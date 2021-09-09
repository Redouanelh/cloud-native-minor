package nl.minor.clsd.presentation;

import nl.minor.clsd.domain.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto entityToAccountDto(Account entity);
    Account accountDtoToEntity(AccountDto dto);
}
