package com.dpico.atsistemas.prices.domain.repository;

import com.dpico.atsistemas.prices.domain.model.Price;

import java.time.*;
import java.util.*;

public interface PriceRepository {

    /**
     * Retrieve the data that match with:
     * @equals - productId
     * @equals - bnandId
     * @between - startDate AND endDate
     *
     * @param productId a Product Identifier
     * @param brandId a Brand ID for the product
     * @param date a valid DateTime (yyyy-MM-dd-HH.mm.ss)
     *
     * @return a List of Price
     */
    List<Price> findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(Long productId, Long brandId, LocalDateTime date);

    Optional<Price> findPriceEntityByProductIdAndBrandIdAndBetweenStartDateAndEndDateAndPriorityNative(Long productId, Long brandId, LocalDateTime date);

}
