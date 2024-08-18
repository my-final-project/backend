package br.com.juhmaran.pet_flow_cloud.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "O campo senha atual é obrigatório e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A senha atual deve ter no mínimo {min} e no máximo {max} caracteres.")
    private String currentPassword;

    @NotBlank(message = "O campo nova senha é obrigatório e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A nova senha deve ter no mínimo {min} e no máximo {max} caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "A nova senha deve ter no mínimo: 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial.")
    private String newPassword;

    @NotBlank(message = "O campo confirmar senha é obrigatório e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A confirmação da senha deve ter no mínimo {min} e no máximo {max} caracteres.")
    private String confirmPassword;

}
