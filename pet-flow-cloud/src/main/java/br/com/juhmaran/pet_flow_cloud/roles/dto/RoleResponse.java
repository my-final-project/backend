package br.com.juhmaran.pet_flow_cloud.roles;

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
    private OffsetDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime updatedAt;

}
