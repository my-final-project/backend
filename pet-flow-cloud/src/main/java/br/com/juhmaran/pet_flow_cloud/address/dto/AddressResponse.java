package br.com.juhmaran.pet_flow_cloud.address.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private String zipCode; // CEP
    private String number; // Numero
    private String street; // Logradouro
    private String complement; // Complemento
    private String neighborhood; // Bairro
    private String city; // Cidade
    private String state; // UF

}
