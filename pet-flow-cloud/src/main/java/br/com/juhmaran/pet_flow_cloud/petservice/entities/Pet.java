package br.com.juhmaran.pet_flow_cloud.petservice;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    private UUID uuid;

    @Column(nullable = false)
    private String name; // Nome do animal

    @Column(nullable = false)
    private String species; // Espécie do animal

    @Column
    private String breed; // Raça do animal

    @Column
    private LocalDate dateOfBirth; // Data de nascimento do animal

    @Column
    private String sex; // Sexo do animal

    @Column
    private Double weight; // Peso atual do animal

    @Column
    private String color; // Cor do animal

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> medicalHistory; // Histórico médico do animal

//    @ManyToMany
//    @JoinTable(
//            name = "pet_owners",
//            joinColumns = @JoinColumn(name = "pet_id"),
//            inverseJoinColumns = @JoinColumn(name = "owner_id")
//    )
//    private List<UUID> owners; // Lista de IDs de donos associados ao animal

    @Column
    private String observations; // Observações adicionais sobre o animal

}
