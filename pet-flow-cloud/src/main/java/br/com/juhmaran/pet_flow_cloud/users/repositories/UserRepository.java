package br.com.juhmaran.pet_flow_cloud.users.repositories;

import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Page<User> findByNameContainingAndCpfContainingAndEmailContaining(String name, String cpf,
                                                                      String email, Pageable pageable);

}
