package br.com.juhmaran.pet_flow_cloud.users.dto;

import br.com.juhmaran.pet_flow_cloud.users.entities.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private UserStatus status;

    private Set<String> roles;

    private OffsetDateTime createdDate;

    private OffsetDateTime lastModifiedDate;

}
