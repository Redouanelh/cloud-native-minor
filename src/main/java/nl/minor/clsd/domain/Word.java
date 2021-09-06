package nl.minor.clsd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    private String word;

    public String reverseWord() {
        return new StringBuilder(this.word).reverse().toString();
    }
}
