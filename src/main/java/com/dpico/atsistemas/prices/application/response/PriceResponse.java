package com.dpico.atsistemas.prices.application.response;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceResponse {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private Date startDate;
    private Date endDate;
    private Float price;
}
