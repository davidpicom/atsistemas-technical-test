package com.dpico.atsistemas.prices.application.response;

import com.fasterxml.jackson.annotation.*;
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
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss", timezone="Europe/Madrid")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss", timezone="Europe/Madrid")
    private Date endDate;
    private Float price;
}
