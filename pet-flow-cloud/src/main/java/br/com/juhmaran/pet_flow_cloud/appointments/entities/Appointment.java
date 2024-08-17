package br.com.juhmaran.pet_flow_cloud.appointments.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;

}
