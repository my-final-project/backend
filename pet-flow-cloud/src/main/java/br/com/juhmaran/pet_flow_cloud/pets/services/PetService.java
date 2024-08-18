package br.com.juhmaran.pet_flow_cloud.pets.services;

import br.com.juhmaran.pet_flow_cloud.exceptions.ResourceNotFoundException;
import br.com.juhmaran.pet_flow_cloud.pets.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.pets.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.pets.entities.Pet;
import br.com.juhmaran.pet_flow_cloud.pets.mapping.PetMapper;
import br.com.juhmaran.pet_flow_cloud.pets.repositories.PetRepository;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import br.com.juhmaran.pet_flow_cloud.users.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetMapper petMapper;
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Transactional
    public PetResponse createPet(PetRequest petRequest) {
        log.debug("### [PetService] - Creating a new pet with request: {}", petRequest);

        User owner = userRepository.findById(petRequest.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.not.found", null, Locale.getDefault())));

        Pet pet = petMapper.toEntity(petRequest);
        pet.setOwner(owner);

        Pet savedPet = petRepository.save(pet);
        log.info("### [PetService] - Pet created successfully with id: {}", savedPet.getId());
        return petMapper.toResponse(savedPet);
    }

    @Transactional
    public PetResponse updatePet(Long id, PetRequest petRequest) {
        log.debug("### [PetService] - Updating pet with id: {} and request: {}", id, petRequest);

        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("### [PetService] - Pet not found with id: {}", id);
                    return new ResourceNotFoundException(
                            messageSource.getMessage("pet.not.found", new Object[]{id}, Locale.getDefault()));
                });

        if (petRequest.getName() != null)
            existingPet.setName(petRequest.getName());

        if (petRequest.getSpecies() != null)
            existingPet.setSpecies(petRequest.getSpecies());

        if (petRequest.getBreed() != null)
            existingPet.setBreed(petRequest.getBreed());

        if (petRequest.getColor() != null)
            existingPet.setColor(petRequest.getColor());

        if (petRequest.getDateOfBirth() != null)
            existingPet.setDateOfBirth(petRequest.getDateOfBirth());

        existingPet.setLastModifiedDate(OffsetDateTime.now());
        Pet updatedPet = petRepository.save(existingPet);
        log.info("### [PetService] - Pet updated successfully with id: {}", updatedPet.getId());

        String successMessage = messageSource
                .getMessage("pet.updated.success", new Object[]{updatedPet.getId()}, Locale.getDefault());

        log.info(successMessage);
        return petMapper.toResponse(updatedPet);
    }

    @Transactional(readOnly = true)
    public PetResponse getPetById(Long id) {
        log.debug("### [PetService] - Fetching pet by id: {}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("### [PetService] - Pet not found with id: {}", id);
                    return new ResourceNotFoundException(
                            messageSource.getMessage("pet.not.found", null, Locale.getDefault()));
                });

        log.info("### [PetService] - Pet retrieved successfully with id: {}", id);
        return petMapper.toResponse(pet);
    }

    @Transactional(readOnly = true)
    public Page<PetResponse> searchPets(String name, String species, String breed, String color,
                                        LocalDate dateOfBirth, Pageable pageable) {
        log.debug("### [PetService] - Searching pets with parameters - Name: {}, " +
                        "Species: {}, Breed: {}, Color: {}, Date of Birth: {}",
                name, species, breed, color, dateOfBirth);

        Page<Pet> petsPage = petRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null)
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));

            if (species != null)
                predicates.add(cb.like(cb.lower(root.get("species")), "%" + species.toLowerCase() + "%"));

            if (breed != null)
                predicates.add(cb.like(cb.lower(root.get("breed")), "%" + breed.toLowerCase() + "%"));

            if (color != null)
                predicates.add(cb.like(cb.lower(root.get("color")), "%" + color.toLowerCase() + "%"));

            if (dateOfBirth != null)
                predicates.add(cb.equal(root.get("dateOfBirth"), dateOfBirth));

            return cb.and(predicates.toArray(new Predicate[0]));

        }, pageable);

        log.info("### [PetService] - Pets search completed. Total pets found: {}", petsPage.getTotalElements());
        return petsPage.map(petMapper::toResponse);
    }

    @Transactional
    public void deletePet(Long id) {
        log.debug("### [PetService] - Deleting pet with id: {}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("### [PetService] - Pet not found with id: {}", id);
                    return new ResourceNotFoundException(
                            messageSource.getMessage("pet.not.found", new Object[]{id}, Locale.getDefault()));
                });

        petRepository.delete(pet);
        log.info("### [PetService] - Pet deleted successfully with id: {}", id);

        String successMessage = messageSource
                .getMessage("pet.deleted.success", new Object[]{id}, Locale.getDefault());
        log.info(successMessage);
    }

}
