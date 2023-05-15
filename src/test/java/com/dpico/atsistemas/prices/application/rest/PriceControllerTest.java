package com.dpico.atsistemas.prices.application.rest;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.*;

import java.time.*;
import java.time.format.*;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ComponentScan(basePackages = {"com.dpico.atsistemas.prices.infrastructure", "com.dpico.atsistemas.prices.application"})
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final long productId = 35455;

    private final long brandId = 1;

    @Test
    void shouldReturnRecord2() throws Exception {
        // Test data
        final LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

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
