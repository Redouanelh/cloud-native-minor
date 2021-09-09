package nl.minor.clsd.application;

import nl.minor.clsd.application.error.FailedToSaveObjectException;
import nl.minor.clsd.domain.entity.AccountHolder;
import nl.minor.clsd.repository.AccountHolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountHolderService extends BaseService<AccountHolder> {

    private AccountHolderRepository accountHolderRepository;

    public AccountHolderService(AccountHolderRepository accountHolderRepository) {
        super(accountHolderRepository);
        this.accountHolderRepository = accountHolderRepository;
    }

    @Transactional
    public AccountHolder saveAccountHolder(String firstname, String lastname) {
        AccountHolder savedAccountHolder = this.accountHolderRepository.save(new AccountHolder(firstname, lastname));

        if (savedAccountHolder.getId() == null) throw new FailedToSaveObjectException("Failed to save Account Holder.");
        return savedAccountHolder;
    }
}
