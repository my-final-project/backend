package br.com.juhmaran.pet_flow_cloud.appointments.services;

import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentRequest;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentResponse;
import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import br.com.juhmaran.pet_flow_cloud.appointments.mapping.AppointmentMapper;
import br.com.juhmaran.pet_flow_cloud.appointments.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.toResponseList(appointments);
    }

    public AppointmentResponse getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.map(appointmentMapper::toResponse).orElse(null);
    }

    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentMapper.toEntity(appointmentRequest);
        appointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(appointment);
    }

    public AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setUserId(appointmentRequest.getUserId());
            appointment.setPetId(appointmentRequest.getPetId());
            appointment.setServiceId(appointmentRequest.getServiceId());
            appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
            appointment.setStatus(appointmentRequest.getStatus());
            appointment = appointmentRepository.save(appointment);
            return appointmentMapper.toResponse(appointment);
        }
        return null;
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
