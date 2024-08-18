package br.com.juhmaran.pet_flow_cloud.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "A senha atual é obrigatória e não pode ser nula.")
    @NotBlank(message = "A senha atual é obrigatória e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A senha deve ter no mínimo 8 e no máximo 50 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "A senha atual deve ter no mínimo: 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial.")
    private String currentPassword;

    @NotNull(message = "A nova senha é obrigatória e não pode ser nula.")
    @NotBlank(message = "A nova senha é obrigatória e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A senha deve ter no mínimo 8 e no máximo 50 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "A senha deve ter no mínimo: 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial.")
    private String newPassword;

    @NotNull(message = "A confirmação da senha é obrigatória e não pode ser nula.")
    @NotBlank(message = "A confirmação da senha é obrigatória e não pode estar em branco.")
    @Size(min = 8, max = 50, message = "A confirmação da senha deve ter no mínimo 8 e no máximo 50 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "A confirmação da senha deve ser idêntica à senha.")
    private String confirmPassword;

}
