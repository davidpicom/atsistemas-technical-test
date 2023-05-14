package com.dpico.atsistemas.prices.application.rest;

import com.dpico.atsistemas.prices.application.exception.*;
import com.dpico.atsistemas.prices.application.mapper.*;
import com.dpico.atsistemas.prices.application.response.*;
import com.dpico.atsistemas.prices.domain.model.*;
import com.dpico.atsistemas.prices.domain.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping
public class PriceController {

    private final PriceService priceService;

    private final PriceMapper priceMapper;

    @Autowired
    public PriceController(PriceService priceService, PriceMapper priceMapper) {
        this.priceService = priceService;
        this.priceMapper = priceMapper;
    }

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE },
            value = "/price")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam Long productId,
                                                  @RequestParam Long brandId,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime date){
        Optional<Price> optionalPrice = priceService.getPriceForProductAndBrandAndDate(productId, brandId, date);
        return optionalPrice
                .map(price -> new ResponseEntity<>(priceMapper.toResponse(price), HttpStatus.OK))
                .orElseThrow(() -> new RecordNotFoundException("No Price found for productId: " + productId + ", brandId: " + brandId + ", date: " + date));
    }
}
