package nl.minor.clsd.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.minor.clsd.domain.AccountStatus;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends BaseEntity {
    @Column(name = "iban")
    private String iban;

    @Column(name = "saldo")
    private double saldo;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @ManyToMany
    @JoinTable(
            name = "account_rule",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "accountHolder_id"))
    private Set<AccountHolder> accountHolders;
}
