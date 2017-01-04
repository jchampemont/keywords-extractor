package com.jeanchampemont.nlp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class EnglishKeywordsExtractorTest extends AbstractKeywordsExtractorTest {

    @Before
    public void init() {
        extractor = new KeywordsExtractor(new KeywordsExtractorConfiguration(StemmerLanguage.ENGLISH, 0.25d));
    }

    @Test
    public void bbcCoUkLondonPubs() throws IOException {
        doTest(openExampleFile("bbc.co.uk-london-pubs.txt"));
    }

    @Test
    public void bbcCoUkVegqn() throws IOException {
        doTest(openExampleFile("bbc.co.uk-vegan.txt"));
    }
}
