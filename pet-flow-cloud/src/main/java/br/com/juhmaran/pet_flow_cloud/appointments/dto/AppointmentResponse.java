package br.com.juhmaran.pet_flow_cloud.appointments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @JsonProperty("last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

}
