package com.dpico.atsistemas.prices.domain.model;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {
    private Long id;
    private Long brandId;
    private Date startDate;
    private Date endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Float price;
    private String curr;
}
