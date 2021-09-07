package nl.minor.clsd.application;

import nl.minor.clsd.domain.Word;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    public WordService() {}

    public Word reverseWord(String input) {
        Word word = new Word(input);

        return word.reverseWord();
    }

    public int countAmountOfWords(String text) {
        Word word = new Word(text);

        return word.countWords();
    }
}
