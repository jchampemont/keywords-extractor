package com.jeanchampemont.nlp;

/**
 * Keyword class
 *
 * A Keyword is a stemmed word and it's relevance.
 *
 * The higher the relevance the more relevant the word is
 */
public class Keyword implements Comparable<Keyword> {

    private String word;

    private Double relevance;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    @Override
    public int compareTo(Keyword o) {
        return -(relevance.compareTo(o.getRelevance()));
    }

    @Override
    public String toString() {
        return "[" +
                "W='" + word + '\'' +
                ", R=" + relevance +
                "]\n";
    }
}
