package br.com.juhmaran.pet_flow_cloud.petservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {

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

}
