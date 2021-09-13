package nl.minor.clsd.repository;

import nl.minor.clsd.domain.entity.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
    Integer deleteById(int id);
}
