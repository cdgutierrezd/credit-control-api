package com.project.credit_control.model.dto.sale;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleResponse {
    private Integer id;
    private Integer clientId;
    private String clientName;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
}
