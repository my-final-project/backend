package br.com.juhmaran.pet_flow_cloud.pets.controllers;

import br.com.juhmaran.pet_flow_cloud.pets.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.pets.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.pets.services.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final MessageSource messageSource;

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@Valid @RequestBody PetRequest petRequest) {
        log.debug("### [PetController] - Received request to create pet: {}", petRequest);
        PetResponse petResponse = petService.createPet(petRequest);
        log.info("### [PetController] - Pet created successfully with response: {}", petResponse);
        return new ResponseEntity<>(petResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> getPetById(@PathVariable Long id) {
        log.debug("### [PetController] - Received request to get pet by id: {}", id);
        PetResponse petResponse = petService.getPetById(id);
        log.info("### [PetController] - Pet retrieved successfully with id: {}", id);
        return ResponseEntity.ok(petResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PetResponse>> searchPets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            Pageable pageable) {
        log.debug("### [PetController] - Received request to search pets with parameters: " +
                        "name={}, species={}, breed={}, color={}, dateOfBirth={}",
                name, species, breed, color, dateOfBirth);
        Page<PetResponse> pets = petService.searchPets(name, species, breed, color, dateOfBirth, pageable);
        log.info("### [PetController] - Pets search completed. Found {} pets.", pets.getTotalElements());
        return ResponseEntity.ok(pets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePet(@PathVariable Long id,
                                            @Valid @RequestBody PetRequest petRequest,
                                            Locale locale) {
        log.debug("### [PetController] - Received request to update pet with id: {}", id);
        petService.updatePet(id, petRequest);
        String successMessage = messageSource.getMessage("pet.updated.success", null, locale);
        log.info("### [PetController] - Pet updated successfully with id: {}", id);
        return ResponseEntity.ok(successMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id,
                                            Locale locale) {
        log.debug("### [PetController] - Received request to delete pet with id: {}", id);
        petService.deletePet(id);
        String successMessage = messageSource.getMessage("pet.deleted.success", null, locale);
        log.info("### [PetController] - Pet deleted successfully with id: {}", id);
        return ResponseEntity.ok(successMessage);
    }

}
