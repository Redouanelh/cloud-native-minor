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

    public Word reverseWord() {
        this.word = new StringBuilder(this.word).reverse().toString();

        return this;
    }
}
