package com.dpico.atsistemas.prices.infrastructure.repository.h2;

import com.dpico.atsistemas.prices.infrastructure.dbo.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate")
    List<PriceEntity> findByProductIdAndBrandIdAndBetweenStartDateAndEndDate(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("date") Date date);

}
