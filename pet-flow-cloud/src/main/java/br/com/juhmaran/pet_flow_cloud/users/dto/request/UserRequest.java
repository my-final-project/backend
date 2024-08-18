package br.com.juhmaran.pet_flow_cloud.users.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "O nome é obrigatório e não pode ser nulo.")
    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    @Size(min = 3, max = 150, message = "O nome deve ter no mínimo 3 e no máximo 150 caracteres.")
    private String name;

    @NotNull(message = "O CPF é obrigatório e não pode ser nulo.")
    @NotBlank(message = "O CPF é obrigatório e não pode estar em branco.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato: xxx.xxx.xxx-xx.")
    @CPF(message = "O CPF deve ser um número válido.")
    private String cpf;

    @NotNull(message = "O email é obrigatório e não pode ser nulo.")
    @NotBlank(message = "O email é obrigatório e não pode estar em branco.")
    @Email(message = "O email deve ser válido.")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres.")
    private String email;

    @NotNull(message = "A senha é obrigatória e não pode ser nula.")
    @NotBlank(message = "A senha é obrigatória e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A senha deve ter no mínimo 8 e no máximo 50 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "A senha deve ter no mínimo: 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial.")
    private String password;

    @NotNull(message = "A confirmação da senha é obrigatória e não pode ser nula.")
    @NotBlank(message = "A confirmação da senha é obrigatória e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A confirmação da senha deve ter no mínimo 8 e no máximo 50 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "A confirmação da senha deve ser idêntica à senha.")
    private String confirmPassword;

}
