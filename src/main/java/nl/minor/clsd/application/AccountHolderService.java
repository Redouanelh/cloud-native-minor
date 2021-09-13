package nl.minor.clsd.application;

import nl.minor.clsd.application.error.AccountHolderHasAccountException;
import nl.minor.clsd.application.error.FailedToSaveObjectException;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.domain.entity.AccountHolder;
import nl.minor.clsd.repository.AccountHolderRepository;
import nl.minor.clsd.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderService extends BaseService<AccountHolder> {

    private AccountHolderRepository accountHolderRepository;
    private AccountRepository accountRepository;

    public AccountHolderService(AccountHolderRepository accountHolderRepository, AccountRepository accountRepository) {
        super(accountHolderRepository);
        this.accountHolderRepository = accountHolderRepository;
        this.accountRepository = accountRepository;
    }

    public AccountHolder getById(int id) {
        Optional<AccountHolder> accountHolder = this.accountHolderRepository.findById(id);

        if (accountHolder.isEmpty()) throw new NotFoundException(String.format("AccountHolder with id %s was not found.", id));
        else return accountHolder.get();
    }

    @Transactional
    public AccountHolder saveAccountHolder(String firstname, String lastname) {
        AccountHolder savedAccountHolder = this.accountHolderRepository.save(new AccountHolder(firstname, lastname));

        if (savedAccountHolder.getId() == null) throw new FailedToSaveObjectException("Failed to save Account Holder.");
        return savedAccountHolder;
    }

    @Transactional
    public Integer deleteHolderById(int id) {
        Optional<AccountHolder> accountHolder = this.accountHolderRepository.findById(id);
        if (accountHolder.isEmpty()) throw new NotFoundException(String.format("AccountHolder with id %s was not found, no need to delete it.", id));

        List<Account> accounts = this.accountRepository.findAllByAccountHolders(accountHolder.get());
        if (accounts.size() > 0) throw new AccountHolderHasAccountException(String.format("AccountHolder with id %s is connected to one/multiple accounts. First remove the AccountHolder from those account(s).", id));

        return this.accountHolderRepository.deleteById(id);
    }
}
