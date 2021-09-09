package nl.minor.clsd.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.minor.clsd.domain.entity.Account;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDto {
    private String firstName;
    private String lastName;
    private Set<Account> accounts;
}
