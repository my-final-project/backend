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

    @NotBlank(message = "field.required.not_blank")
    @Size(min = 3, max = 80, message = "field.min_max.size")
    private String name;

    @NotBlank(message = "field.required.not_blank")
    @Size(max = 80, message = "field.max.size")
    private String species; // espécie

    @Size(max = 80, message = "field.max.size")
    private String breed; // raça

    @Size(max = 20, message = "field.max.size")
    private String color;

    @Past(message = "field.past.date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;

    private Long ownerId;

}
