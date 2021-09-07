package nl.minor.clsd.application;

import lombok.extern.slf4j.Slf4j;
import nl.minor.clsd.data.WordRepository;
import nl.minor.clsd.domain.Word;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dev")
public class WordServiceDev implements IWordService {

    private WordRepository wordRepository;

    public WordServiceDev(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word reverseWord(String input) {
        Word word = new Word(input);

        return word.reverseWord();
    }

    public int countAmountOfWords(String text) {
        if (this.wordRepository.requestedTexts.containsKey(text)) return this.wordRepository.requestedTexts.get(text);
        else {
            log.info("New text! Adding it to local storage...");

            Word word = new Word(text);
            this.wordRepository.requestedTexts.put(text, word.countWords());

            return word.countWords();
        }
    }
}
