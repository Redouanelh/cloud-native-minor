package nl.minor.clsd.presentation.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iban4j.CountryCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDto implements Serializable {
    private CountryCode countryCode;
    private String bankCode;
    private Long accountNr;
}
