package com.jeanchampemont.nlp;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.jeanchampemont.nlp.internal.org.tartarus.snowball.SnowballStemmer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main class for the keywords-extractor library.
 */
public class KeywordsExtractor {
    private static final String RESOURCES_PATH = "/lang/";

    private static final String ALPHABET_FILENAME = "alphabet.txt";

    private static final String STOP_WORDS_FILENAME = "stop.txt";

    private SnowballStemmer stemmer;

    private final Set<Character> alphabet = new HashSet<>();

    private final Set<String> stopWords = new HashSet<>();

    private final Double percentile;

    public KeywordsExtractor(KeywordsExtractorConfiguration configuration) {
        Preconditions.checkNotNull(configuration);
        stemmer = configuration.getLanguage().getStemmer();
        readAlphabet(configuration.getLanguage());
        readStopWords(configuration.getLanguage());
        percentile = configuration.getPercentile();
    }

    /**
     * Extract a list of keywords for the specified text
     *
     * @param text
     * @return list of main keywords
     * @throws IOException when error while reading from stream occurs
     */
    public List<Keyword> extract(InputStream text) throws IOException {
        Preconditions.checkNotNull(text);

        BufferedReader reader = new BufferedReader(new InputStreamReader(text));

        HashMultiset<String> stemmedWords = HashMultiset.<String>create();
        Multimap<String, String> wordsByStem = ArrayListMultimap.<String, String>create();

        StringBuilder currentWord = new StringBuilder();
        int ci = reader.read();
        boolean isNewSentence = true;
        boolean stemCurrentWord = true;
        while (ci != -1) { //browsing the text, char by char
            char c = (char) Character.toLowerCase(ci);
            if (alphabet.contains(c)) { //if current char is in the alphabet
                if (!isNewSentence && Character.isUpperCase(ci)) {
                    stemCurrentWord = false;
                }
                currentWord.append(c); //it is the next char of the current word
                isNewSentence = false;
            } else {                   //else we have a word!
                String word = currentWord.toString();
                currentWord = new StringBuilder();
                if (!word.isEmpty() && !stopWords.contains(word)) { //if the word is not a stop word
                    String stemmedWord = word;
                    if (stemCurrentWord) {
                        stemmer.setCurrent(word);
                        stemmer.stem();
                        stemmedWord = stemmer.getCurrent();
                        wordsByStem.put(stemmedWord, word);
                    }
                    stemmedWords.add(stemmedWord);
                }

                if (c == '.') {
                    isNewSentence = true;
                }
                stemCurrentWord = true;
            }
            ci = reader.read();
        }
        //counting words and computing relevance
        int totalWordsCount = stemmedWords.size();
        List<Keyword> result = new ArrayList<>();
        for (String stem : stemmedWords.elementSet()) {
            Keyword keyword = new Keyword();
            keyword.setStem(stem);
            keyword.setRelevance(stemmedWords.count(stem) / (double) totalWordsCount);
            keyword.setWords(new HashSet<>(wordsByStem.get(stem)));
            result.add(keyword);
        }
        Collections.sort(result);
        double relevanceSum = 0;
        int i = 0;
        //keeping only the specified percentile of keywords
        while (relevanceSum < percentile) {
            relevanceSum += result.get(i).getRelevance();
            i++;
        }
        return result.subList(0, Math.max(1, i - 1));
    }

    private void readAlphabet(StemmerLanguage language) {
        String filename = new StringBuilder()
                .append(RESOURCES_PATH)
                .append(getLocalSensitiveDirectoryName(language))
                .append('/')
                .append(ALPHABET_FILENAME)
                .toString();

        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));

        try {
            String line = reader.readLine();
            while (line != null) {
                alphabet.add(line.charAt(0));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readStopWords(StemmerLanguage language) {
        String filename = new StringBuilder()
                .append(RESOURCES_PATH)
                .append(getLocalSensitiveDirectoryName(language))
                .append('/')
                .append(STOP_WORDS_FILENAME)
                .toString();

        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));

        try {
            String line = reader.readLine();
            while (line != null) {
                stopWords.add(line.trim());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getLocalSensitiveDirectoryName(StemmerLanguage language) {
        return language.getCode();
    }
}
