package nl.minor.clsd.presentation;

import lombok.*;
import nl.minor.clsd.domain.AccountStatus;
import nl.minor.clsd.domain.entity.AccountHolder;
import org.iban4j.Iban;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Iban iban;
    @NotNull
    @DecimalMin("1")
    private BigDecimal saldo;
    private AccountStatus accountStatus = AccountStatus.ACTIVE;
    private Set<AccountHolder> accountHolders;
}
