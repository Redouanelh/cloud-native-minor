package nl.minor.clsd.application;

import nl.minor.clsd.domain.Word;

public interface IWordService {
    Word reverseWord(String input);
    int countAmountOfWords(String text);
}
