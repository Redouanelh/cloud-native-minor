package nl.minor.clsd.application;

import lombok.extern.slf4j.Slf4j;
import nl.minor.clsd.domain.Word;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("prod")
public class WordServiceProd implements IWordService {

    /* It is still called reverseWord, but for the assignment with 'profiles' it now returns the word in full caps. */
    public Word reverseWord(String input) {
        return new Word(input.toUpperCase());
    }

    public int countAmountOfWords(String text) {
        return 0;
    }
}
