package com.dpico.atsistemas.prices.application.mapper;

import com.dpico.atsistemas.prices.application.response.*;
import com.dpico.atsistemas.prices.domain.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceResponse toResponse(Price price);

}
