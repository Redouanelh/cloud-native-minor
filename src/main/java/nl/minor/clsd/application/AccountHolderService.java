package nl.minor.clsd.application;

import nl.minor.clsd.domain.entity.AccountHolder;
import nl.minor.clsd.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService extends BaseService<AccountHolder> {

    private AccountHolderRepository accountHolderRepository;

    private AccountHolderService(AccountHolderRepository accountHolderRepository) {
        super(accountHolderRepository);
        this.accountHolderRepository = accountHolderRepository;
    }
}
