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

    @NotBlank(message = "field.required.not_blank")
    @Size(min = 8, max = 50, message = "field.min_max.size")
    private String currentPassword;

    @NotBlank(message = "field.required.not_blank")
    @Size(min = 8, max = 50, message = "field.min_max.size")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "field.password.pattern")
    private String newPassword;

    @NotBlank(message = "field.required.not_blank")
    @Size(min = 8, max = 50, message = "field.min_max.size")
    private String confirmPassword;

}
