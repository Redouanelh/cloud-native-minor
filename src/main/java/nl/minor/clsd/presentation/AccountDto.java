package nl.minor.clsd.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.minor.clsd.domain.AccountStatus;
import nl.minor.clsd.domain.entity.AccountHolder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String iban;
    private double saldo;
    private AccountStatus accountStatus;
    private Set<AccountHolder> accountHolders;
}
