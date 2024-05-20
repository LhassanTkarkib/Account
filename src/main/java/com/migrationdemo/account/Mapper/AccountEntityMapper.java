package com.migrationdemo.account.Mapper;

import com.migrationdemo.account.DTOs.AccountEntityDto;
import com.migrationdemo.account.Entity.AccountEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountEntityMapper {
    AccountEntity toEntity(AccountEntityDto accountEntityDto);

    AccountEntityDto toDto(AccountEntity accountEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountEntity partialUpdate(AccountEntityDto accountEntityDto, @MappingTarget AccountEntity accountEntity);
}