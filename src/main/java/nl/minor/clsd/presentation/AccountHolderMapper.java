package nl.minor.clsd.presentation;

import nl.minor.clsd.domain.entity.AccountHolder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountHolderMapper {
    AccountHolderMapper INSTANCE = Mappers.getMapper( AccountHolderMapper.class );

    AccountHolderDto entityToAccountHolderDto(AccountHolder entity);
    AccountHolder accountHolderDtoToEntity(AccountHolderDto dto);
}
