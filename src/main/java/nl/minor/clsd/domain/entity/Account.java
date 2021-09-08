package nl.minor.clsd.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.minor.clsd.domain.AccountStatus;

import javax.persistence.*;

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
}
