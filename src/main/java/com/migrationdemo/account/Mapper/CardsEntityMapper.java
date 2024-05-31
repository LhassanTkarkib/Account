package com.migrationdemo.account.Mapper;

import com.migrationdemo.account.DTOs.CardsEntityDto;
import com.migrationdemo.account.Entity.CardsEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardsEntityMapper {
    CardsEntity toEntity(CardsEntityDto cardsEntityDto);

    CardsEntityDto toDto(CardsEntity cardsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CardsEntity partialUpdate(CardsEntityDto cardsEntityDto, @MappingTarget CardsEntity cardsEntity);
}