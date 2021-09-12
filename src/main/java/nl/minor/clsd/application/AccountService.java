package nl.minor.clsd.application;

import nl.minor.clsd.application.error.FailedToSaveObjectException;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.repository.AccountRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.iban4j.bban.BbanEntryType.account_number;

@Service
public class AccountService extends BaseService<Account> {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account saveAccount(CountryCode countryCode, String bankCode, long accountNr) {
        String iban = this.generateIban(countryCode, bankCode, accountNr);

        Account savedAccount = this.accountRepository.save(new Account(iban));
        if (savedAccount.getId() == null) throw new FailedToSaveObjectException("Failed to save Account.");

        return savedAccount;
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
