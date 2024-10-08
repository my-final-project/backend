package br.com.juhmaran.pet_flow_cloud.roles.entities;

import br.com.juhmaran.pet_flow_cloud.utils.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "uk_name", columnNames = "name")
})
public class Role extends BaseEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    private RoleType name;

    @Column(nullable = false)
    private String description;

}
