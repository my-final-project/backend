package br.com.juhmaran.pet_flow_cloud.appointments;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;
    private Long userId;
    private Long petId;
    private Long serviceId;
    private OffsetDateTime appointmentDate;
    private String status;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;

}
