package com.project.credit_control.model.dto.payment;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavePaymentRequest {
    private Integer clientId;
    private BigDecimal amount;
}
