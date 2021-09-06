package nl.minor.clsd.application;

import nl.minor.clsd.domain.Word;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    public WordService() {}

    public Word reverseWord(String input) {
        Word word = new Word(input);

        return new Word(word.reverseWord());
    }
}
