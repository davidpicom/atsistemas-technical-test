package com.dpico.atsistemas.prices.application.rest;


import com.dpico.atsistemas.prices.domain.model.*;
import com.dpico.atsistemas.prices.domain.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.*;

import java.sql.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ComponentScan(basePackages = {"com.dpico.atsistemas.prices.infrastructure", "com.dpico.atsistemas.prices.application"})
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private Price record2;

    private final long productId = 35455;

    private final long brandId = 1;

    @BeforeEach
    void setUp() {
        record2 = Price.builder()
                .id(2L).brandId(brandId)
                .startDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 15, 0, 0)))
                .endDate(Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 18, 30, 0)))
                .priceList(2L).productId(productId).priority(1).price(25.45F).curr("EUR")
                .build();
    }

    @Test
    void shouldReturnRecord2() throws Exception {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

        given(priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate))
                .willReturn(Optional.of(record2));

        this.mockMvc.perform(get("/price")
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("date", testDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.curr").doesNotExist());
    }

    @Test
    void shouldReturn404() throws Exception {
        // Test data
        final long productId = 354555;
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

        given(priceService.getPriceForProductAndBrandAndDate(productId, brandId, testDate))
                .willReturn(Optional.empty());

        this.mockMvc.perform(get("/price")
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("date", testDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400() throws Exception {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

        this.mockMvc.perform(get("/price")
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("date", testDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss")))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Failed to convert date. Pattern expected (yyyy-MM-dd-HH.mm.ss)")));
    }

    @Test
    void shouldReturn500() throws Exception {
        // Test data
        final String productId = "35455a";
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

        this.mockMvc.perform(get("/price")
                        .param("productId", productId)
                        .param("brandId", String.valueOf(brandId))
                        .param("date", testDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss")))
                )
                .andExpect(status().isInternalServerError());
    }

}
