package br.com.juhmaran.pet_flow_cloud.appointments.entities;

import br.com.juhmaran.pet_flow_cloud.utils.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity implements Serializable {

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "pet_id")
    private Long petId;

    @Column(nullable = false, name = "service_id")
    private Long serviceId;

    @Column(nullable = false, name = "appointment_date")
    private OffsetDateTime appointmentDate;

    @Column(nullable = false)
    private String status;

}
