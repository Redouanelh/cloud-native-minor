package nl.minor.clsd.domain.entity;

import lombok.*;
import nl.minor.clsd.domain.AccountStatus;
import nl.minor.clsd.domain.IbanToStringConverter;
import org.iban4j.Iban;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends BaseEntity {
    @Convert(converter = IbanToStringConverter.class)
    @Column(name = "iban")
    private Iban iban;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @ManyToMany
    @JoinTable(
            name = "account_rule",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "accountHolder_id"))
    private Set<AccountHolder> accountHolders;

    public Set<AccountHolder> getAccountHolders() {
        return Collections.unmodifiableSet(accountHolders);
    }

    public boolean addAccountHolder(AccountHolder holder) {
        return accountHolders.add(holder);
    }

    public boolean removeAccountHolder(UUID accountHolderId) {
        return accountHolders.removeIf(holder -> holder.getId().equals(accountHolderId));
    }
}
