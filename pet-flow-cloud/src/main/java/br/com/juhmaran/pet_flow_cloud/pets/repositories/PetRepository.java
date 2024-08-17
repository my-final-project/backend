package br.com.juhmaran.pet_flow_cloud.pets.repositories;

import br.com.juhmaran.pet_flow_cloud.pets.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PetRepository extends JpaRepository<Pet, Long>,
        JpaSpecificationExecutor<Pet> {

}
