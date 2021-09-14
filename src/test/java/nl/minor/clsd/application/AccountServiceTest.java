package nl.minor.clsd.application;

import nl.minor.clsd.application.AccountService;
import nl.minor.clsd.application.error.AlreadyExistsException;
import nl.minor.clsd.application.error.FailedToSaveObjectException;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.domain.entity.AccountHolder;
import nl.minor.clsd.repository.AccountRepository;
import org.iban4j.CountryCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository mockAccountRepository;

    @Mock
    AccountHolderService mockAccountHolderService;

    @InjectMocks
    AccountService accountService;

    @Captor
    ArgumentCaptor<Account> accountCaptor;

    Account account;
    Account secondAccount;
    Account thirdAccount;
    AccountHolder accountHolder;

    @BeforeEach
    void beforeEach() {
        this.account = new Account("NL69INGB0123456789");
        this.secondAccount = new Account("NL69INGB2222222222");
        this.thirdAccount = new Account("NL35ABNA0000000124");
        this.accountHolder = new AccountHolder("Redouan", "Kaasje");
    }

    @Test
    void find_account_by_iban() {
        Mockito.when(this.mockAccountRepository.findByIban("NL69INGB0123456789")).thenReturn(java.util.Optional.ofNullable(this.account));
        var result = this.accountService.findByIban("NL69INGB0123456789");

        assertThat(result.getIban()).isEqualTo("NL69INGB0123456789");
        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).findByIban("NL69INGB0123456789");
    }

    @Test
    void throw_correct_exception_when_iban_not_found() {
        Mockito.when(this.mockAccountRepository.findByIban("NL45ABNA0123456789")).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            this.accountService.findByIban("NL45ABNA0123456789");
        });

        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).findByIban("NL45ABNA0123456789");
    }

    @Test
    void find_accounts_by_holder() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(this.account);
        accounts.add(this.secondAccount);

        Mockito.when(this.mockAccountHolderService.getById(1)).thenReturn(this.accountHolder);
        Mockito.when(this.mockAccountRepository.findAllByAccountHolders(this.accountHolder)).thenReturn(accounts);

        var result = this.accountService.findAllByHolder(1);

        assertThat(result.contains(this.account)).isTrue();
        assertThat(result.contains(this.secondAccount)).isTrue();
        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).findAllByAccountHolders(this.accountHolder);
    }

    @Test
    void save_account() {
        this.thirdAccount.setId(1);

        Mockito.when(this.mockAccountRepository.findByIban("NL35ABNA0000000124")).thenReturn(Optional.empty()); // account does not exist yet
        Mockito.when(this.mockAccountRepository.save(any())).thenReturn(this.thirdAccount);

        var result = this.accountService.saveAccount(CountryCode.NL, "ABNA", 124);

        assertThat(result.getIban()).isEqualTo("NL35ABNA0000000124");

        // Captor annotation voor het opvangen van complexe objecten die parameters gebruiken.
        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).save(this.accountCaptor.capture());
        assertThat(this.accountCaptor.getValue().getIban()).isEqualTo("NL35ABNA0000000124");
    }

    @Test
    void save_account_already_exists() {
        Mockito.when(this.mockAccountRepository.findByIban("NL35ABNA0000000124")).thenReturn(java.util.Optional.ofNullable(this.account));

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            this.accountService.saveAccount(CountryCode.NL, "ABNA", 124);
        });

        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).findByIban("NL35ABNA0000000124");
    }

    @Test
    void save_account_failed() {
        Mockito.when(this.mockAccountRepository.findByIban("NL35ABNA0000000124")).thenReturn(Optional.empty()); // account does not exist yet
        Mockito.when(this.mockAccountRepository.save(any())).thenReturn(new Account()); // empty account without data (for example: id or iban)

        Assertions.assertThrows(FailedToSaveObjectException.class, () -> {
            this.accountService.saveAccount(CountryCode.NL, "ABNA", 124);
        });

        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).findByIban("NL35ABNA0000000124");
        Mockito.verify(this.mockAccountRepository, Mockito.times(1)).save(this.accountCaptor.capture());
    }
}
