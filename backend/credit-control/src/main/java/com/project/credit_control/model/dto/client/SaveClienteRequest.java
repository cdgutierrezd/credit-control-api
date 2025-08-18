package com.project.credit_control.model.dto.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveClienteRequest {
    private String name;
    private String address;
}
