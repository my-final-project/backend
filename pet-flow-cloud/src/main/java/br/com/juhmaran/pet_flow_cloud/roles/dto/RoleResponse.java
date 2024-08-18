package br.com.juhmaran.pet_flow_cloud.roles.dto;

import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {

    private Long id;

    private RoleType name;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public OffsetDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public OffsetDateTime lastModifiedDate;

}
