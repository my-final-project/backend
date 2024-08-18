package br.com.juhmaran.pet_flow_cloud.pets.mapping;

import br.com.juhmaran.pet_flow_cloud.pets.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.pets.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.pets.entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    Pet toEntity(PetRequest petRequest);

    @Mapping(target = "age", expression = "java(pet.getAge())")
    @Mapping(target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    PetResponse toResponse(Pet pet);

    List<PetResponse> toResponseList(List<Pet> pets);

}
