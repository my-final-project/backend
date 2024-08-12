package br.com.juhmaran.pet_flow_cloud.petservice.mapping;

import br.com.juhmaran.pet_flow_cloud.petservice.dto.PetRequest;
import br.com.juhmaran.pet_flow_cloud.petservice.dto.PetResponse;
import br.com.juhmaran.pet_flow_cloud.petservice.entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Facilita o mapeamento entre a entidade e os DTOs, reduzindo a boilerplate de código
 *
 * @author juliane.maran
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
        // Configuração de formatação de data
    PetResponse petToPetResponse(Pet pet);

    @Mapping(target = "id", ignore = true) // Ignorar UUID durante a conversão do DTO para a entidade
    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
        // Configuração de formatação de data
    Pet petRequestToPet(PetRequest petRequest);

}
