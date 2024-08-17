package br.com.juhmaran.pet_flow_cloud.service;

import lombok.*;

import java.math.BigDecimal;

/**
 * Representa o animal de estimação do usuário
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    private String name;
    private BigDecimal price;
    private String description;

}
