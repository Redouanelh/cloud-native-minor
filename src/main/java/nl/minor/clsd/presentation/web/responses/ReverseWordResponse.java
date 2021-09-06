package nl.minor.clsd.presentation.web.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ReverseWordResponse implements Serializable {
    private String word;
}
