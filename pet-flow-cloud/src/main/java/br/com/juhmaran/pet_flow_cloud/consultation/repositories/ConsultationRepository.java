package br.com.juhmaran.pet_flow_cloud.consultation.repositories;

import br.com.juhmaran.pet_flow_cloud.consultation.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
