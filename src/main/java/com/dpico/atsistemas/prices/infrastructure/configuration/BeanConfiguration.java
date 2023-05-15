package com.dpico.atsistemas.prices.infrastructure.configuration;

import com.dpico.atsistemas.prices.domain.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.dpico.atsistemas.prices.domain.repository.PriceRepository;

@Configuration
public class BeanConfiguration {

    @Bean
    PriceService priceService(PriceRepository priceRepository) {
        return new DomainPriceService(priceRepository);
    }
}
