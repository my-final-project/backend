package br.com.juhmaran.pet_flow_cloud.users.entities;

import br.com.juhmaran.pet_flow_cloud.utils.base.BaseEntity;
import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_cpf", columnNames = "cpf")
})
public class User extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "FK_user_role"),
            inverseForeignKey = @ForeignKey(name = "FK_role_user")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

}
