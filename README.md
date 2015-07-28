# Keywords Extractor

This project is a small prototype to extract important keywords from a text.

This is using the snowball stemmer (http://snowball.tartarus.org/).

For now, only french language is implemented, but it should be easy to implement any other language having a snowball stemmer. 
Just add the `alphabet.txt` (all possible characters of the language) and `stop.txt` (language specific stop words) in resources/lang/.

The extracted keywords are stemmed words, so they might not be human-readable or real words, but they could be processed by a computer program.
