package br.com.juhmaran.pet_flow_cloud.appointments.services;

import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentRequest;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentResponse;
import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import br.com.juhmaran.pet_flow_cloud.appointments.mapping.AppointmentMapper;
import br.com.juhmaran.pet_flow_cloud.appointments.repositories.AppointmentRepository;
import br.com.juhmaran.pet_flow_cloud.exceptions.ResourceNotFoundException;
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
        log.info("Fetching all appointments.");
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.toResponseList(appointments);
    }

    public AppointmentResponse getAppointmentById(Long id) {
        log.info("Fetching appointment with ID: {}", id);
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if (appointment.isPresent()) {
            return appointmentMapper.toResponse(appointment.get());
        }
        log.error("Appointment with ID: {} not found", id);
        throw new ResourceNotFoundException("Appointment not found with ID: " + id);
    }

    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        log.info("Creating new appointment");
        Appointment appointment = appointmentMapper.toEntity(appointmentRequest);
        appointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(appointment);
    }

    public AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        log.info("Updating appointment with ID: {}", id);
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
        } else {
            log.error("Appointment with ID: {} not found", id);
            throw new ResourceNotFoundException("Appointment not found with ID: " + id);
        }
    }

    public void deleteAppointment(Long id) {
        log.info("Deleting appointment with ID: {}", id);
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            log.error("Appointment with ID: {} not found", id);
            throw new ResourceNotFoundException("Appointment not found with ID: " + id);
        }
    }

}
