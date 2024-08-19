package br.com.juhmaran.pet_flow_cloud.users.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "field.required.not_null")
    @NotBlank(message = "field.required.not_blank")
    @Size(min = 3, max = 150, message = "field.min_max.size")
    private String name;

    @CPF(message = "field.cpf.invalid")
    @NotNull(message = "field.required.not_null")
    @NotBlank(message = "field.required.not_blank")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "field.pattern")
    private String cpf;

    @NotNull(message = "field.required.not_null")
    @NotBlank(message = "field.required.not_blank")
    @Email(message = "field.email.invalid")
    @Size(max = 150, message = "field.max.size")
    private String email;

    @NotNull(message = "field.required.not_null")
    @NotBlank(message = "field.required.not_blank")
    @Size(min = 8, max = 50, message = "field.min_max.size")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "field.password.pattern")
    private String password;

    //    @NotNull(message = "field.required.not_null")
//    @NotBlank(message = "field.required.not_blank")
    @Size(min = 8, max = 50, message = "field.min_max.size")
    private String confirmPassword;

    private Set<Long> petsId;

}
