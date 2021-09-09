package nl.minor.clsd.application;

import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService extends BaseService<Account> {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account createAccount(int accountHolderId) {

        return null;
    }

}
