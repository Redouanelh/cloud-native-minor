package nl.minor.clsd.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.minor.clsd.domain.AccountStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String iban;
    private double saldo;
    private AccountStatus accountStatus;
}
