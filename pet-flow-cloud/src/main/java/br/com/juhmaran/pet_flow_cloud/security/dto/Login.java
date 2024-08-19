package br.com.juhmaran.pet_flow_cloud.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotBlank(message = "O campo e-mail não pode ser nulo ou em branco.")
    @Email(message = "Formato do e-mail está inválido.",
            regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;

    @NotBlank(message = "O campo senha não pode ser nulo ou em branco.")
    @Size(min = 8, max = 50,
            message = "O tamanho do campo senha deve estar entre {min} e {max} digitos.")
    private String password;

}
