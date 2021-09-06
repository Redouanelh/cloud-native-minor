package nl.minor.clsd.presentation.web.controller;

import nl.minor.clsd.application.WordService;
import nl.minor.clsd.application.error.WordToShortException;
import nl.minor.clsd.domain.Word;
import nl.minor.clsd.presentation.web.requests.ReverseWordRequest;
import nl.minor.clsd.presentation.web.responses.ReverseWordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("word")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PutMapping("/reverse")
    public ResponseEntity<ReverseWordResponse> reverseWord(@RequestBody ReverseWordRequest request) {
        if (request.getWord().length() < 2) throw new WordToShortException("Word needs to have atleast 2 characters!");

        Word word = this.wordService.reverseWord(request.getWord());

        return ResponseEntity.ok(new ReverseWordResponse(word.getWord()));
    }
}
