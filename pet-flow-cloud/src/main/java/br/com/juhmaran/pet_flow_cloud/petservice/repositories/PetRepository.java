package br.com.juhmaran.pet_flow_cloud.petservice.repositories;

import br.com.juhmaran.pet_flow_cloud.petservice.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface do repositório para acessar os dados da entidade 'Pet'
 *
 * @author juliane.maran
 */
public interface PetRepository extends JpaRepository<Pet, Long> {
}
