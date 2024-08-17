package br.com.juhmaran.pet_flow_cloud.service.mapping;

import br.com.juhmaran.pet_flow_cloud.service.dto.ServiceRequest;
import br.com.juhmaran.pet_flow_cloud.service.dto.ServiceResponse;
import br.com.juhmaran.pet_flow_cloud.service.entities.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    @Mapping(target = "id", ignore = true)
    Services toEntity(ServiceRequest serviceRequest);

    ServiceResponse toResponse(Services services);

    List<ServiceResponse> toResponseList(List<Services> services);

}
