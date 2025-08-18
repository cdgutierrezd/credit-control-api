package com.project.credit_control.model.dto.sale;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveSaleRequest {

    private Integer clientId;
    private BigDecimal amount;
    private String description;

}
