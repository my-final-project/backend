package br.com.juhmaran.pet_flow_cloud.users.repositories;

import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
