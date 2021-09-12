package nl.minor.clsd.repository;

import nl.minor.clsd.domain.entity.Account;
import org.iban4j.Iban;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByIban(Iban iban);
}
