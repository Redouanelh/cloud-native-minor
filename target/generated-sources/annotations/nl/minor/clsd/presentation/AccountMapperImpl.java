package nl.minor.clsd.presentation;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.domain.entity.AccountHolder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-21T11:56:02+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.4 (JetBrains s.r.o)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto entityToAccountDto(Account entity) {
        if ( entity == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setIban( entity.getIban() );
        accountDto.setSaldo( entity.getSaldo() );
        accountDto.setAccountStatus( entity.getAccountStatus() );
        Set<AccountHolder> set = entity.getAccountHolders();
        if ( set != null ) {
            accountDto.setAccountHolders( new HashSet<AccountHolder>( set ) );
        }

        return accountDto;
    }

    @Override
    public Account accountDtoToEntity(AccountDto dto) {
        if ( dto == null ) {
            return null;
        }

        Account account = new Account();

        account.setIban( dto.getIban() );
        account.setSaldo( dto.getSaldo() );
        account.setAccountStatus( dto.getAccountStatus() );
        Set<AccountHolder> set = dto.getAccountHolders();
        if ( set != null ) {
            account.setAccountHolders( new HashSet<AccountHolder>( set ) );
        }

        return account;
    }
}
