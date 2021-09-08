package nl.minor.clsd.domain.Dto;

import nl.minor.clsd.domain.entity.AccountHolder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountHolderMapper {
    @Mappings({
            @Mapping(target = "firstName", source = "entity.firstName"),
            @Mapping(target = "lastName", source = "entity.lastName"),
    })
    AccountHolderDto toDto(AccountHolder entity);
    @Mappings({
            @Mapping(target = "firstName", source = "dto.firstName"),
            @Mapping(target = "lastName", source = "dto.lastName"),
    })
    AccountHolder toEntity(AccountHolderDto dto);
}
