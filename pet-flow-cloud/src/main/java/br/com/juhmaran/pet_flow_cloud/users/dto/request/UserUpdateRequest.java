package br.com.juhmaran.pet_flow_cloud.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @Size(min = 3, max = 150, message = "O nome deve ter no mínimo 3 e no máximo 150 caracteres.")
    private String name;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "O CPF deve estar no formato: xxx.xxx.xxx-xx.")
    private String cpf;

    @Email(message = "O email deve ser válido.")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres.")
    private String email;

}
