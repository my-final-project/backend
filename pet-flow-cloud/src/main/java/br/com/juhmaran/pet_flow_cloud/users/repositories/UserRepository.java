package br.com.juhmaran.pet_flow_cloud.users.repositories;

import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Page<User> findByNameContainingAndCpfContainingAndEmailContaining(String name, String cpf,
                                                                      String email, Pageable pageable);

    Optional<User> findByEmail(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u JOIN u.roles r WHERE r.name = :roleName")
    boolean existsByRoleName(RoleType roleName);

}
