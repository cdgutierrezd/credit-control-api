package com.project.credit_control.model.dto.client;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {
    private Integer id;
    private String name;
    private BigDecimal debt;
    private String address;
}
