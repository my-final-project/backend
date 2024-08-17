package br.com.juhmaran.pet_flow_cloud.pets.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * Representa o animal de estimação do usuário
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {

    @NotBlank(message = "O campo nome não pode ser nulo ou em branco.")
    @Size(min = 3, max = 80, message = "O campo nome deve ter entre 3 e 80 caracteres.")
    private String name;

    @NotBlank(message = "O campo espécie não pode ser nulo ou em branco.")
    @Size(max = 80, message = "O campo espécie deve ter no máximo 80 caracteres.")
    private String species; // espécie

    @Size(max = 80, message = "O campo raça deve ter no máximo 80 caracteres.")
    private String breed; // raça

    @Size(max = 20, message = "O campo cor deve ter no máximo 20 caracteres.")
    private String color;

    @Past(message = "A data de nascimento deve estar no passado.")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;

}
