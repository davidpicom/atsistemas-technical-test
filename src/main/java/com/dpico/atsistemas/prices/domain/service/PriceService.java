package com.dpico.atsistemas.prices.domain.service;

import com.dpico.atsistemas.prices.domain.model.*;

import java.time.*;
import java.util.*;

public interface PriceService {

    Optional<Price> getPriceForProductAndBrandAndDate(Long productId, Long brandId, LocalDateTime date);
}
