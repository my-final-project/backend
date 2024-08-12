package br.com.juhmaran.pet_flow_cloud.petservice.controllers;

import br.com.juhmaran.pet_flow_cloud.petservice.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.petservice.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.petservice.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Define os endpoints REST e usa o serviço para realizar operações
 *
 * @author juliane.maran
 */
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetResponse>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponse> getPetById(@PathVariable(name = "petId") Long petId) {
        return ResponseEntity.ok(petService.getPetById(petId));
    }

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@RequestBody PetRequest petRequest) {
        PetResponse petResponse = petService.createPet(petRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponse);
    }


    @PutMapping("/{petId}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable(name = "petId") Long petId,
                                                 @RequestBody PetRequest petRequest) {
        PetResponse petResponse = petService.updatePet(petId, petRequest);
        return ResponseEntity.ok(petResponse);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable(name = "petId") Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }

}
