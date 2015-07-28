package com.jeanchampemont.nlp;

import com.google.common.base.Preconditions;

/**
 * A KeywordsExtractor Configuration.
 *
 * Language is the language of the text for the stemmer.
 *
 * Percentile is the top percentil of keywords to keep.
 * 0.25 seems to be a good value.
 */
public class KeywordsExtractorConfiguration {
    private StemmerLanguage language;

    private Double percentile;

    public KeywordsExtractorConfiguration(StemmerLanguage language, Double percentile) {
        Preconditions.checkNotNull(language);
        Preconditions.checkNotNull(percentile);
        this.language = language;
        this.percentile = percentile;
    }

    public StemmerLanguage getLanguage() {
        return language;
    }

    public void setLanguage(StemmerLanguage language) {
        this.language = language;
    }

    public Double getPercentile() {
        return percentile;
    }

    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }
}
