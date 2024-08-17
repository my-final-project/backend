package br.com.juhmaran.pet_flow_cloud.service.repositories;

import br.com.juhmaran.pet_flow_cloud.service.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
