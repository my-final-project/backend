package br.com.juhmaran.pet_flow_cloud.consultation.entities;

import br.com.juhmaran.pet_flow_cloud.utils.base.BaseEntity;
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
@Table(name = "consultations")
public class Consultation extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private Long veterinarianId;

    @Column(nullable = false)
    private OffsetDateTime consultationDate;

    @Column(nullable = false, length = 500)
    private String notes;

}
