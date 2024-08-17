package br.com.juhmaran.pet_flow_cloud.consultation;

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
public class ConsultationRequest {

    private Long petId;
    private Long veterinarianId;
    private OffsetDateTime consultationDate;
    private String notes;
}
