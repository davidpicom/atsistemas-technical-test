package com.dpico.atsistemas.prices.application.exception;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private int statusCode;
    private Date date;
    private String message;
    private String description;
}
