package com.dpico.atsistemas.prices.infrastructure.mapper;

import com.dpico.atsistemas.prices.infrastructure.dbo.*;
import com.dpico.atsistemas.prices.domain.model.Price;
import org.mapstruct.*;

import java.util.*;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toDomain(PriceEntity priceEntity);

    List<Price> toDomainList(List<PriceEntity> priceEntityList);

    PriceEntity toEntity(Price price);
}
