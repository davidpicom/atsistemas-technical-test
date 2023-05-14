package com.dpico.atsistemas.prices.infrastructure.repository.h2;

import com.dpico.atsistemas.prices.domain.model.*;
import com.dpico.atsistemas.prices.domain.repository.*;
import com.dpico.atsistemas.prices.infrastructure.dbo.*;
import com.dpico.atsistemas.prices.infrastructure.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.time.*;
import java.time.format.*;
import java.util.Date;
import java.util.*;

@Component
public class H2DbPriceRepository implements PriceRepository {

    private final SpringDataPriceRepository priceRepository;

    private final PriceEntityMapper priceMapper;

    @Autowired
    public H2DbPriceRepository(SpringDataPriceRepository priceRepository, PriceEntityMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Price> findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(Long productId, Long brandId, LocalDateTime date) {
        Instant instant = Timestamp.valueOf(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).toInstant();
        Date queryDate = Date.from(instant);
        List<PriceEntity> pricesList = priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, queryDate);
        return priceMapper.toDomainList(pricesList);
    }

}
