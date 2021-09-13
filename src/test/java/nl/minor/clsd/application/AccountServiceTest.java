package nl.minor.clsd.application;

import nl.minor.clsd.application.AccountService;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository mockAccountRepository;

    @InjectMocks
    AccountService accountService;

    Account account = new Account("NL69INGB0123456789");

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
}
