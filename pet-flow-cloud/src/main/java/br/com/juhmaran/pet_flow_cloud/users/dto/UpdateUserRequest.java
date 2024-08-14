package br.com.juhmaran.pet_flow_cloud.users.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @Size(min = 3, max = 250, message = "Nome: O nome deve ter entre {min} e {max} caracteres.")
    private String name;

    @Pattern(regexp = "^\\+?\\d{9,14}$",
            message = "Número de Telefone: Por favor, insira um número de telefone que tenha entre 9 e 14 dígitos numéricos. O sinal de + é opcional.")
    private String phoneNumber;

}
