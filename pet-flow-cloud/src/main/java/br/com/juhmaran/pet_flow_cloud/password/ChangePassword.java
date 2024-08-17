package br.com.juhmaran.pet_flow_cloud.password;

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
public class ChangePassword {

    @NotBlank(message = "O campo senha atual não pode ser nulo ou em branco.")
    @Size(min = 8, max = 50,
            message = "O tamanho do campo senha atual deve estar entre {min} e {max} digitos.")
    private String currentPassword;

    @NotBlank(message = "O campo nova senha não pode ser nulo ou em branco.")
    @Size(min = 8, max = 50,
            message = "O tamanho do campo nova senha deve estar entre {min} e {max} digitos.")
    private String newPassword;

    @NotBlank(message = "O campo confirmar senha não pode ser nulo ou em branco.")
    @Size(min = 8, max = 50,
            message = "O tamanho do campo confirmar senha deve estar entre {min} e {max} digitos.")
    private String confirmPassword;

}
