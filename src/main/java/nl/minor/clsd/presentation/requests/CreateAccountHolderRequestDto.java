package nl.minor.clsd.presentation.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountHolderRequestDto implements Serializable {
    private String firstName;
    private String lastName;
}
