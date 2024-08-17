package br.com.juhmaran.pet_flow_cloud.consultation.mapping;

import br.com.juhmaran.pet_flow_cloud.consultation.dto.ConsultationRequest;
import br.com.juhmaran.pet_flow_cloud.consultation.dto.ConsultationResponse;
import br.com.juhmaran.pet_flow_cloud.consultation.entities.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsultationMapper {

    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Consultation toEntity(ConsultationRequest consultationRequest);

    @Mapping(target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "lastModifiedDate", dateFormat = "yyyy-MM-dd HH:mm")
    ConsultationResponse toResponse(Consultation consultation);

    List<ConsultationResponse> toResponseList(List<Consultation> consultations);

}
