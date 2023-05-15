package com.dpico.atsistemas.prices.infraestructure.repository;

import com.dpico.atsistemas.prices.infrastructure.configuration.*;
import com.dpico.atsistemas.prices.infrastructure.dbo.*;
import com.dpico.atsistemas.prices.infrastructure.repository.h2.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.context.annotation.*;

import java.sql.*;
import java.time.*;
import java.util.Date;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(H2Configuration.class)
class H2DbPriceRepositoryTest {

    @Autowired
    private SpringDataPriceRepository priceRepository;

    private PriceEntity record1, record2, record3, record4;

    private final long productId = 35455;

    private final long brandId = 1;

    @BeforeEach
    void setUp() {
        record1 = PriceEntity.builder()
                .id(1L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 0, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 12, 31, 23, 59, 59)))
                .priceList(1L).productId(productId).priority(0).price(35.50F).curr("EUR")
                .build();

        record2 = PriceEntity.builder()
                .id(2L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 15, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 18, 30, 0)))
                .priceList(2L).productId(productId).priority(1).price(25.45F).curr("EUR")
                .build();

        record3 = PriceEntity.builder()
                .id(3L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 0, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 11, 00, 0)))
                .priceList(3L).productId(productId).priority(1).price(30.50F).curr("EUR")
                .build();

        record4 = PriceEntity.builder()
                .id(4L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 16, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 12, 31, 23, 59, 59)))
                .priceList(4L).productId(productId).priority(1).price(38.95F).curr("EUR")
                .build();
    }

    @Test
    void shouldFindAll_thenSize4() {
        // Given data
        final List<PriceEntity> pricesList = priceRepository.findAll();

        assertThat(pricesList).hasSize(4);
    }

    @Test
    void shouldFindPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority_thenReturnRecord1() {
        final Date testDate = Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 0, 0, 0));

        final PriceEntity price = priceRepository.findPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority(productId, brandId, testDate);

        assertThat(price).isEqualTo(record1);
    }

    @Test
    void shouldFindPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority_thenReturnRecord1AndRecord2() {
        final Date testDate = Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 16, 0, 0));

        final PriceEntity price = priceRepository.findPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority(productId, brandId, testDate);

        assertThat(price).isEqualTo(record2);
    }

    @Test
    void shouldFindPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority_thenReturnRecord1AndRecord3() {
        final Date testDate = Timestamp.valueOf(LocalDateTime.of(2020, 6, 15, 10, 0, 0));

        final PriceEntity price = priceRepository.findPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority(productId, brandId, testDate);

        assertThat(price).isEqualTo(record3);
    }

    @Test
    void shouldFindPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority_thenReturnRecord1AndRecord4() {
        final Date testDate = Timestamp.valueOf(LocalDateTime.of(2020, 6, 16, 21, 0, 0));

        final PriceEntity price = priceRepository.findPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriority(productId, brandId, testDate);

        assertThat(price).isEqualTo(record4);
    }

    @Test
    void shouldDeleteAll_thenIsEmpty(){
        priceRepository.deleteAll();

        assertThat(priceRepository.findAll()).isEmpty();
    }

}
