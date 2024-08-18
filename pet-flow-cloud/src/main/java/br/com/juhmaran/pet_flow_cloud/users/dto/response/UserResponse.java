package br.com.juhmaran.pet_flow_cloud.users.dto.response;

import br.com.juhmaran.pet_flow_cloud.users.entities.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Set<Long> petsId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public OffsetDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public OffsetDateTime lastModifiedDate;
}
