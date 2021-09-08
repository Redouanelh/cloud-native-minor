package nl.minor.clsd.domain.Dto;

import nl.minor.clsd.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mappings({
            @Mapping(target = "iban", source = "entity.iban"),
            @Mapping(target = "saldo", source = "entity.saldo"),
            @Mapping(target = "accountStatus", source = "entity.accountStatus")
    })
    AccountDto toDto(Account entity);
    @Mappings({
            @Mapping(target = "iban", source = "dto.iban"),
            @Mapping(target = "saldo", source = "dto.saldo"),
            @Mapping(target = "accountStatus", source = "dto.accountStatus")
    })
    Account toEntity(AccountDto dto);
}
