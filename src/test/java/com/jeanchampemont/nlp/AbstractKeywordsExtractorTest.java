package com.jeanchampemont.nlp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jchampem on 04/01/2017.
 */
public class AbstractKeywordsExtractorTest {
    protected KeywordsExtractor extractor;

    protected InputStream openExampleFile(String name) {
        String filename = "/examples/" + name;
        return getClass().getResourceAsStream(filename);
    }

    protected void doTest(InputStream stream) throws IOException {
        List<Keyword> keywords = extractor.extract(stream);
        System.out.println(keywords);
    }
}
