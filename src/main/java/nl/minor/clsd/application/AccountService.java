package nl.minor.clsd.application;

import nl.minor.clsd.application.error.AccountAlreadyBlockedException;
import nl.minor.clsd.application.error.AlreadyExistsException;
import nl.minor.clsd.application.error.FailedToSaveObjectException;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.domain.AccountStatus;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.domain.entity.AccountHolder;
import nl.minor.clsd.repository.AccountRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.iban4j.bban.BbanEntryType.account_number;

@Service
public class AccountService extends BaseService<Account> {

    private AccountRepository accountRepository;
    private AccountHolderService accountHolderService;

    public AccountService(AccountRepository accountRepository, AccountHolderService accountHolderService) {
        super(accountRepository);
        this.accountRepository = accountRepository;
        this.accountHolderService = accountHolderService;
    }

    public Account findByIban(String iban) {
        Optional<Account> account = this.accountRepository.findByIban(iban);
        if (account.isEmpty()) throw new NotFoundException(String.format("Account with iban %s was not found.", iban));

        else return account.get();
    }

    @Transactional
    public Account saveAccount(CountryCode countryCode, String bankCode, long accountNr) {
        String iban = this.generateIban(countryCode, bankCode, accountNr);
        if (this.accountRepository.findByIban(iban).isPresent()) throw new AlreadyExistsException(String.format("Account with iban %s already exists", iban));

        Account savedAccount = this.accountRepository.save(new Account(iban));
        if (savedAccount.getId() == null) throw new FailedToSaveObjectException("Failed to save Account.");

        return savedAccount;
    }

    @Transactional
    public Account addAccountHolder(String iban, int id) {
        Optional<Account> account = this.accountRepository.findByIban(iban);
        if (account.isEmpty()) throw new NotFoundException(String.format("Account with iban %s was not found.", iban));

        AccountHolder accountHolder = this.accountHolderService.getById(id);
        if (account.get().getAccountHolders().contains(accountHolder)) throw new AlreadyExistsException(String.format("Account with iban %s already has AccountHolder with id %s", iban, id));
        else {
            account.get().addAccountHolder(accountHolder);
            return account.get();
        }
    }

    // Remove holder from account (ook check of ie er uberhuapt wel in zit)

    @Transactional
    public Account blockAccount(String iban) {
        Optional<Account> account = this.accountRepository.findByIban(iban);

        if (account.isEmpty()) throw new NotFoundException(String.format("Account with iban %s was not found.", iban));
        if (account.get().getAccountStatus().equals(AccountStatus.BLOCKED)) throw new AccountAlreadyBlockedException(String.format("Account with iban %s was already blocked.", iban));
        else {
            account.get().setAccountStatus(AccountStatus.BLOCKED);
            return account.get();
        }
    }

    @Transactional
    public Integer deleteByIban(String iban) {
        return this.accountRepository.deleteByIban(iban);
    }

    private String generateIban(CountryCode countryCode, String bankCode, long accountNr) {
        int accountNumberLength = BbanStructure.forCountry(countryCode).getEntries().stream()
                .filter(e -> e.getEntryType().equals(account_number))
                .findFirst()
                .map(BbanStructureEntry::getLength)
                .orElse(0);
        String formatParam = "%0" + accountNumberLength + "d";
        String accountNumber = String.format(formatParam, accountNr);
        return new Iban.Builder()
                .countryCode(countryCode)
                .bankCode(bankCode)
                .accountNumber(accountNumber)
                .buildRandom()
                .toString();
    }

}
