package br.com.juhmaran.pet_flow_cloud.pets.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Separar os DTOs para requisição e resposta ajuda a isolar a estrutura de dados do cliente e do servidor.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {

    private Long id; // Identificador único do animal

    private String name; // Nome do animal

    private String species; // Espécie do animal

    private String breed; // Raça do animal

    private LocalDate dateOfBirth; // Data de nascimento do animal

    private String sex; // Sexo do animal

    private Double weight; // Peso atual do animal

    private String color; // Cor do animal

    private List<String> medicalHistory; // Histórico médico do animal

//    private List<UUID> owners; // Lista de IDs de donos associados ao animal

    private String observations; // Observações adicionais sobre o animal

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

}
