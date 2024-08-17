package br.com.juhmaran.pet_flow_cloud.consultation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

/**
 * Representa o animal de estimação do usuário
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationResponse {

    private Long id;
    private Long petId;
    private Long veterinarianId;
    private OffsetDateTime consultationDate;
    private String notes;

    @JsonProperty("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @JsonProperty("last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

}
