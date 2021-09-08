package nl.minor.clsd.repository;

import nl.minor.clsd.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountHolderRepository {
    private Map<Integer, List<Account>> accountHoldersLocalStorage = new HashMap<>();
}
