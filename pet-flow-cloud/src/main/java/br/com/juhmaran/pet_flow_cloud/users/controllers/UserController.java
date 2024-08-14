package br.com.juhmaran.pet_flow_cloud.users.controllers;

import br.com.juhmaran.pet_flow_cloud.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body("Obeter usuários");
    }

//    @PutMapping(value = "/update")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserRequest request)
//            throws InvalidRequestException, ResourceNotFoundException, ServiceException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        try {
//            userService.updateUser(currentPrincipalName, request);
//            log.info("Usuário atualizado com sucesso: {}", currentPrincipalName);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário atualizado com sucesso!");
//        } catch (InvalidRequestException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo inválido.");
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
//        } catch (ServiceException e) {
//            log.error("Erro ao atualizar usuário", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário.");
//        }
//    }
//
//    @DeleteMapping(value = "/delete")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<String> deactivateUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        try {
//            userService.deactivateUser(currentPrincipalName);
//            log.info("Usuário desativado com sucesso: {}", currentPrincipalName);
//            return ResponseEntity.status(HttpStatus.OK).body("Usuário desativado com sucesso!");
//            // return ResponseEntity.ok("Usuário desativado com sucesso!");
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (ServiceException e) {
//            log.error("Erro ao desativar usuário", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

}
