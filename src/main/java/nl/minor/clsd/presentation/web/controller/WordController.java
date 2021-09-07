package nl.minor.clsd.presentation.web.controller;

import nl.minor.clsd.application.IWordService;
import nl.minor.clsd.application.WordServiceDev;
import nl.minor.clsd.application.error.MoreThanOneWordException;
import nl.minor.clsd.application.error.WordToShortException;
import nl.minor.clsd.domain.Word;
import nl.minor.clsd.presentation.web.requests.CountWordsInTextRequest;
import nl.minor.clsd.presentation.web.requests.ReverseWordRequest;
import nl.minor.clsd.presentation.web.responses.CountWordsInTextResponse;
import nl.minor.clsd.presentation.web.responses.ReverseWordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("word")
public class WordController {

    private final IWordService wordService;

    public WordController(IWordService wordService) {
        this.wordService = wordService;
    }

    @PutMapping("/reverse")
    public ResponseEntity<ReverseWordResponse> reverseWord(@RequestBody ReverseWordRequest request) {
        if (request.getWord().length() < 2) throw new WordToShortException("Word needs to have atleast 2 characters!");
        if (request.getWord().split("\\s+").length > 1) throw new MoreThanOneWordException("You can only insert one word to reverse!");

        Word word = this.wordService.reverseWord(request.getWord());

        return ResponseEntity.ok(new ReverseWordResponse(word.getWord()));
    }

    @GetMapping("/counter")
    public ResponseEntity<CountWordsInTextResponse> countWordsOfText(@RequestBody CountWordsInTextRequest request) {
        int amountOfWords = this.wordService.countAmountOfWords(request.getText());

        return ResponseEntity.ok(new CountWordsInTextResponse(request.getText(), amountOfWords));
    }
}
