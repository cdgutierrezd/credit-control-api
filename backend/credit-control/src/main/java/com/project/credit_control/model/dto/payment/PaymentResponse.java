package com.project.credit_control.model.dto.payment;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Integer id;
    private Integer clientId;
    private String clientName;
    private BigDecimal amount;
    private LocalDateTime date;
}
