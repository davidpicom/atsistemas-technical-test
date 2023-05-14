package com.dpico.atsistemas.prices.domain.service;

import com.dpico.atsistemas.prices.domain.model.*;
import com.dpico.atsistemas.prices.domain.repository.*;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainPriceServiceTest {

    private PriceRepository priceRepository;

    private DomainPriceService priceService;

    private Price record1, record2, record3, record4;

    private final long productId = 35455;

    private final long brandId = 1;

    @BeforeEach
    void setUp() {
        priceRepository = mock(PriceRepository.class);
        priceService = new DomainPriceService(priceRepository);

        record1 = Price.builder()
                .id(1L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 0, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 12, 31, 23, 59, 59)))
                .priceList(1L).productId(productId).priority(0).price(35.50F).curr("EUR")
                .build();

        record2 = Price.builder()
                .id(2L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 15, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 18, 30, 0)))
                .priceList(2L).productId(productId).priority(1).price(25.45F).curr("EUR")
                .build();

        record3 = Price.builder()
                .id(3L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 0, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 11, 00, 0)))
                .priceList(3L).productId(productId).priority(1).price(30.50F).curr("EUR")
                .build();

        record4 = Price.builder()
                .id(4L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 16, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 12, 31, 23, 59, 59)))
                .priceList(4L).productId(productId).priority(1).price(38.95F).curr("EUR")
                .build();
    }

    @Test
    void shouldReturnRecord1_priceAt10OnDay14() {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);

        // Repository data
        List<Price> prices = List.of(record1);
        when(priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, testDate))
                .thenReturn(prices);

        // Expected result
        final Optional<Price> serviceResultPrice = priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate);

        assertEquals(record1, serviceResultPrice.get());
    }

    @Test
    void shouldReturnRecord2_priceAt16OnDay14() {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

        // Repository data
        List<Price> prices = List.of(record1, record2);
        when(priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, testDate))
                .thenReturn(prices);

        // Expected result
        final Optional<Price> serviceResultPrice = priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate);

        assertEquals(record2, serviceResultPrice.get());
    }

    @Test
    void shouldReturnRecord1_priceAt21OnDay14() {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);

        // Repository data
        List<Price> prices = List.of(record1);
        when(priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, testDate))
                .thenReturn(prices);

        // Expected result
        final Optional<Price> serviceResultPrice = priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate);

        assertEquals(record1, serviceResultPrice.get());
    }

    @Test
    void shouldReturnRecord3_priceAt10OnDay15() {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

        // Repository data
        List<Price> prices = List.of(record1, record3);
        when(priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, testDate))
                .thenReturn(prices);

        // Expected result
        final Optional<Price> serviceResultPrice = priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate);

        assertEquals(record3, serviceResultPrice.get());
    }

    @Test
    void shouldReturnRecord4_priceAt21OnDay16() {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

        // Repository data
        List<Price> prices = List.of(record1, record4);
        when(priceRepository.findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(productId, brandId, testDate))
                .thenReturn(prices);

        // Expected result
        final Optional<Price> serviceResultPrice = priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate);

        assertEquals(record4, serviceResultPrice.get());
    }
}