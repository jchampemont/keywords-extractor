package com.jeanchampemont.nlp;

import java.util.Set;

/**
 * Keyword class
 * <p>
 * A Keyword is a stem its relevance and associated words found in the original text
 * <p>
 * The higher the relevance the more relevant the stem is
 */
public class Keyword implements Comparable<Keyword> {

    private String stem;

    private Double relevance;

    private Set<String> words;

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    @Override
    public int compareTo(Keyword o) {
        return -(relevance.compareTo(o.getRelevance()));
    }

    @Override
    public String toString() {
        return "[" +
                "S='" + stem + '\'' +
                ", R=" + relevance +
                ", W=" + words.toString() +
                "]\n";
    }
}
