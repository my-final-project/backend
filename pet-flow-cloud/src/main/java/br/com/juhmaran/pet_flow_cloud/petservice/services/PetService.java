package br.com.juhmaran.pet_flow_cloud.petservice.services;

import br.com.juhmaran.pet_flow_cloud.petservice.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.petservice.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.petservice.entities.Pet;
import br.com.juhmaran.pet_flow_cloud.petservice.mapping.PetMapper;
import br.com.juhmaran.pet_flow_cloud.petservice.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Encapsula a lógica de negócios e interage com o repositório
 *
 * @author juliane.maran
 */
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetMapper petMapper;
    private final PetRepository petRepository;

    public List<PetResponse> getAllPets() {
        return petRepository.findAll().stream()
                .map(petMapper::petToPetResponse)
                .toList();
    }

    public PetResponse getPetById(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return petMapper.petToPetResponse(pet);
    }

    public PetResponse createPet(PetRequest petRequest) {
        Pet pet = petMapper.petRequestToPet(petRequest);
        Pet savedPet = petRepository.save(pet);
        return petMapper.petToPetResponse(savedPet);
    }

    public PetResponse updatePet(Long petId, PetRequest petRequestDTO) {
        if (!petRepository.existsById(petId)) {
            throw new RuntimeException("Pet not found");
        }
        Pet pet = petMapper.petRequestToPet(petRequestDTO);
        pet.setId(petId);
        Pet updatedPet = petRepository.save(pet);
        return petMapper.petToPetResponse(updatedPet);
    }

    public void deletePet(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new RuntimeException("Pet not found");
        }
        petRepository.deleteById(petId);
    }

}