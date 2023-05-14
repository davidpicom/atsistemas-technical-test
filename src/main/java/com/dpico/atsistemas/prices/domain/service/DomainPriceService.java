package com.dpico.atsistemas.prices.domain.service;

import com.dpico.atsistemas.prices.domain.model.*;
import com.dpico.atsistemas.prices.domain.repository.*;
import org.springframework.beans.factory.annotation.*;

import java.time.*;
import java.util.*;

public class DomainPriceService implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public DomainPriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> getPriceForProductAndBrandAndDate(Long productId, Long brandId, LocalDateTime date) {
        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, date);
        return prices
                .stream()
                .max(Comparator.comparingInt(Price::getPriority));
    }

}
