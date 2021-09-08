package nl.minor.clsd.repository;

import nl.minor.clsd.domain.entity.AccountHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {
    private Map<Integer, List<AccountHolder>> accountsLocalStorage = new HashMap<>();

    // create account

    // block account

    // delete account

    // get all accounts

    // get one account

    // add accountholder
}
