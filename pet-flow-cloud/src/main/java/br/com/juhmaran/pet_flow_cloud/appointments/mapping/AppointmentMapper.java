package br.com.juhmaran.pet_flow_cloud.appointments.mapping;

import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentRequest;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Appointment toEntity(AppointmentRequest appointmentRequest);

    @Mapping(target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "lastModifiedDate", dateFormat = "yyyy-MM-dd HH:mm")
    AppointmentResponse toResponse(Appointment appointment);

    List<AppointmentResponse> toResponseList(List<Appointment> appointments);

}
