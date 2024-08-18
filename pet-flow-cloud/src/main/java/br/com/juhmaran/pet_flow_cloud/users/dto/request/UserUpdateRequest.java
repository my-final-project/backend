package br.com.juhmaran.pet_flow_cloud.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @Size(min = 3, max = 150, message = "field.min_max.size")
    private String name;

    @CPF(message = "field.cpf.invalid")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "field.pattern")
    private String cpf;

    @Email(message = "field.email.invalid")
    @Size(max = 150, message = "field.max.size")
    private String email;

}
