package com.jeanchampemont.nlp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(JUnit4.class)
public class FrenchKeywordsExtractorTest extends AbstractKeywordsExtractorTest {

    @Before
    public void init() {
        extractor = new KeywordsExtractor(new KeywordsExtractorConfiguration(StemmerLanguage.FRENCH, 0.25d));
    }

    @Test
    public void lemondeFrEmploiTxt() throws IOException {
        doTest(openExampleFile("lemonde.fr-emploi.txt"));
    }

    @Test
    public void lemondeFrTunnelTxt() throws IOException {
        doTest(openExampleFile("lemonde.fr-tunnel.txt"));
    }

}
