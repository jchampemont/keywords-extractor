package com.jeanchampemont.nlp;

/**
 * Language to use for the Stemmer
 */
public enum StemmerLanguage {
    FRENCH("fr");

    private String code;

    private StemmerLanguage(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
