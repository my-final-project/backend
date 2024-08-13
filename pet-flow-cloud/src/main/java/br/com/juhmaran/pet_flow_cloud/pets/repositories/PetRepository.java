package br.com.juhmaran.pet_flow_cloud.pets.repositories;

import br.com.juhmaran.pet_flow_cloud.pets.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface do repositório para acessar os dados da entidade 'Pet'
 *
 * @author juliane.maran
 */
public interface PetRepository extends JpaRepository<Pet, Long> {
}
