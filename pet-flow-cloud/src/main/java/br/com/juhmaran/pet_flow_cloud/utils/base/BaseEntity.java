package br.com.juhmaran.pet_flow_cloud.utils.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    public OffsetDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    public OffsetDateTime lastModifiedDate;

}
