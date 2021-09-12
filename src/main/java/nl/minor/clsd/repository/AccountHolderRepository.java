package nl.minor.clsd.repository;

import nl.minor.clsd.domain.entity.AccountHolder;
import org.iban4j.Iban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
    @Query("select distinct h from Account a left join a.accountHolders h where a.iban = ?1")
    List<AccountHolder> findAllByAccountIban(Iban iban);
}
