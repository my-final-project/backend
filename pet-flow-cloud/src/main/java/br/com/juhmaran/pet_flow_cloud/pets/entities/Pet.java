package br.com.juhmaran.pet_flow_cloud.pets.entities;

import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import br.com.juhmaran.pet_flow_cloud.utils.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * Representa o animal de estimação do usuário
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "species", nullable = false, length = 50)
    private String species; // espécie

    @Column(name = "breed", length = 80)
    private String breed; // raça

    @Column(name = "color", length = 20)
    private String color;

    @Transient
    private Integer age;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_pet_user")
    )
    private User owner;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

}
