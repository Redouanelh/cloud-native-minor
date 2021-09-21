package nl.minor.clsd.presentation;

import javax.annotation.processing.Generated;
import nl.minor.clsd.domain.entity.AccountHolder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-21T11:56:02+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.4 (JetBrains s.r.o)"
)
@Component
public class AccountHolderMapperImpl implements AccountHolderMapper {

    @Override
    public AccountHolderDto entityToAccountHolderDto(AccountHolder entity) {
        if ( entity == null ) {
            return null;
        }

        AccountHolderDto accountHolderDto = new AccountHolderDto();

        accountHolderDto.setFirstName( entity.getFirstName() );
        accountHolderDto.setLastName( entity.getLastName() );

        return accountHolderDto;
    }

    @Override
    public AccountHolder accountHolderDtoToEntity(AccountHolderDto dto) {
        if ( dto == null ) {
            return null;
        }

        AccountHolder accountHolder = new AccountHolder();

        accountHolder.setFirstName( dto.getFirstName() );
        accountHolder.setLastName( dto.getLastName() );

        return accountHolder;
    }
}
