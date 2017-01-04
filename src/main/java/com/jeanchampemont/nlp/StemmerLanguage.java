package com.jeanchampemont.nlp;

import com.jeanchampemont.nlp.internal.org.tartarus.snowball.SnowballStemmer;
import com.jeanchampemont.nlp.internal.org.tartarus.snowball.ext.englishStemmer;
import com.jeanchampemont.nlp.internal.org.tartarus.snowball.ext.frenchStemmer;

import java.util.function.Supplier;

/**
 * Language to use for the Stemmer
 */
public enum StemmerLanguage {
    FRENCH("fr", frenchStemmer::new),
    ENGLISH("en", englishStemmer::new);

    private String code;
    private Supplier<SnowballStemmer> stemmerSupplier;

    private StemmerLanguage(String code, Supplier<SnowballStemmer> stemmerSupplier) {
        this.code = code;
        this.stemmerSupplier = stemmerSupplier;
    }

    public String getCode() {
        return code;
    }

    public SnowballStemmer getStemmer() {
        return stemmerSupplier.get();
    }
}
