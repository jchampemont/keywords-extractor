# Keywords Extractor

This project is a small prototype to extract important keywords from a text.

This is using the snowball stemmer (http://snowball.tartarus.org/).

For now, french and english languages are implemented, but it should be easy to implement any other language having a snowball stemmer. 
Just add the `alphabet.txt` (all possible characters of the language) and `stop.txt` (language specific stop words) in resources/lang/.

The extracted keywords are stemmed words, so they might not be human-readable or real words, but they could be processed by a computer program.

# Example output
## English
For this text: [bbc.co.uk-vegan.txt](src/test/resources/examples/bbc.co.uk-vegan.txt), the following top 10 relevant stems were found:

| Stem | Score | Complete word |
|---|---|---|
| vegan | 0.033 | vegan, veganism |
| diet | 0.030 | diet, dietary |
| peopl | 0.020 | people |
| food | 0.018 | food |
|  chang | 0.015 | change, changing |
| egg | 0.013 | egg |
| anim | 0.013 | animal |
| eat | 0.013 | eat, eating |
| nutrient | 0.013 | nutrient |
| meat | 0.013 | meat |

## French
For this text: [lemonde.fr-tunnel.txt](src/test/resources/examples/lemonde.fr-tunnel.txt), the following top 10 relevant stems were found:

| Stem | Score | Words |
|---|---|---|
| migr | 0.041 | migrant, migrer, immigration |
| eurotunnel | 0.026 | eurotunnel |
| tunnel | 0.023 | tunnel |
| million | 0.019 | million |
| précis | 0.019 | précis, précisemment, précision |
| cal | 0.019 | Calais, Nord-Pas-De-Calais |
| sit | 0.019 | site, situation |
| intrus | 0.15 | intrus, intrusion |
| tenta | 0.015 | tentative |
| terminal | 0.015 | terminal |
