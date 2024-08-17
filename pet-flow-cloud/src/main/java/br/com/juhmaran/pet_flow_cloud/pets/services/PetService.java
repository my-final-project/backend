package br.com.juhmaran.pet_flow_cloud.pets.services;

import br.com.juhmaran.pet_flow_cloud.exceptions.ResourceNotFoundException;
import br.com.juhmaran.pet_flow_cloud.pets.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.pets.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.pets.entities.Pet;
import br.com.juhmaran.pet_flow_cloud.pets.mapping.PetMapper;
import br.com.juhmaran.pet_flow_cloud.pets.repositories.PetRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetMapper petMapper;
    private final PetRepository petRepository;

    @Transactional
    public PetResponse createPet(PetRequest petRequest) {
        log.debug("Creating a new pet with request: {}", petRequest);
        Pet pet = petMapper.toEntity(petRequest);
        pet.setCreatedDate(OffsetDateTime.now());
        pet.setLastModifiedDate(OffsetDateTime.now());
        Pet savedPet = petRepository.save(pet);
        log.info("Pet created successfully with id: {}", savedPet.getId());
        return petMapper.toResponse(savedPet);
    }

    @Transactional
    public PetResponse updatePet(Long id, PetRequest petRequest) {
        log.debug("Updating pet with id: {} and request: {}", id, petRequest);
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pet not found with id: {}", id);
                    return new ResourceNotFoundException("Pet not found with id: " + id);
                });
        if (petRequest.getName() != null) existingPet.setName(petRequest.getName());
        if (petRequest.getSpecies() != null) existingPet.setSpecies(petRequest.getSpecies());
        if (petRequest.getBreed() != null) existingPet.setBreed(petRequest.getBreed());
        if (petRequest.getColor() != null) existingPet.setColor(petRequest.getColor());
        if (petRequest.getDateOfBirth() != null) existingPet.setDateOfBirth(petRequest.getDateOfBirth());
        existingPet.setLastModifiedDate(OffsetDateTime.now());
        Pet updatedPet = petRepository.save(existingPet);
        log.info("Pet updated successfully with id: {}", updatedPet.getId());
        return petMapper.toResponse(updatedPet);
    }

    @Transactional(readOnly = true)
    public PetResponse getPetById(Long id) {
        log.debug("Fetching pet by id: {}", id);
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pet not found with id: {}", id);
                    return new ResourceNotFoundException("Pet not found with id: " + id);
                });
        log.info("Pet retrieved successfully with id: {}", id);
        return petMapper.toResponse(pet);
    }

    @Transactional(readOnly = true)
    public Page<PetResponse> searchPets(String name, String species,
                                        String breed, String color,
                                        LocalDate dateOfBirth,
                                        Pageable pageable) {
        log.debug("Searching pets with parameters - Name: {}, Species: {}, Breed: {}, Color: {}, Date of Birth: {}",
                name, species, breed, color, dateOfBirth);
        Page<Pet> petsPage = petRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            if (species != null)
                predicates.add(cb.like(cb.lower(root.get("species")), "%" + species.toLowerCase() + "%"));
            if (breed != null) predicates.add(cb.like(cb.lower(root.get("breed")), "%" + breed.toLowerCase() + "%"));
            if (color != null) predicates.add(cb.like(cb.lower(root.get("color")), "%" + color.toLowerCase() + "%"));
            if (dateOfBirth != null) predicates.add(cb.equal(root.get("dateOfBirth"), dateOfBirth));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        log.info("Pets search completed. Total pets found: {}", petsPage.getTotalElements());
        return petsPage.map(petMapper::toResponse);
    }

    @Transactional
    public void deletePet(Long id) {
        log.debug("Deleting pet with id: {}", id);
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pet not found with id: {}", id);
                    return new ResourceNotFoundException("Pet not found with id: " + id);
                });
        petRepository.delete(pet);
        log.info("Pet deleted successfully with id: {}", id);
    }

}
