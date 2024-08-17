package br.com.juhmaran.pet_flow_cloud.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "O campo nome não pode ser nulo ou em branco.")
    @Size(min = 3, max = 150)
    private String name;

    @CPF(message = "Informe um número de CPF válido!")
    @Size(min = 11, max = 11,
            message = "O tamanho do campo CPF deve estar entre {min} e {max} digitos.")
    private String cpf;

    @NotBlank(message = "O campo e-mail não pode ser nulo ou em branco.")
    @Email(message = "Formato do e-mail está inválido.",
            regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;

    @NotBlank(message = "O campo senha não pode ser nulo ou em branco.")
    @Size(min = 8, max = 50,
            message = "O tamanho do campo senha deve estar entre {min} e {max} digitos.")
    private String password;

    @NotBlank(message = "O campo confirmar senha não pode ser nulo ou em branco.")
    @Size(min = 8, max = 50,
            message = "O tamanho do campo confirmar senha deve estar entre {min} e {max} digitos.")
    private String confirmPassword;

}
