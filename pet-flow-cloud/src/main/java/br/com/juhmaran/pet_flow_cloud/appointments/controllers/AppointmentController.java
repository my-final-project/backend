package br.com.juhmaran.pet_flow_cloud.appointments.controllers;

import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentRequest;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentResponse;
import br.com.juhmaran.pet_flow_cloud.appointments.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentResponse> getAllAppointments() {
        log.info("Fetching all appointments");
        List<AppointmentResponse> appointments = appointmentService.getAllAppointments();
        log.debug("Fetched {} appointments", appointments.size());
        return appointments;
    }

    @GetMapping("/{id}")
    public AppointmentResponse getAppointmentById(@PathVariable Long id) {
        log.info("Fetching appointment with ID: {}", id);
        AppointmentResponse appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            log.debug("Fetched appointment: {}", appointment);
        }
        log.warn("Appointment with ID: {} not found", id);
        return appointment;
    }

    @PostMapping
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        log.info("Creating new appointment");
        AppointmentResponse appointment = appointmentService.createAppointment(appointmentRequest);
        log.debug("Created appointment: {}", appointment);
        return appointment;
    }

    @PutMapping("/{id}")
    public AppointmentResponse updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        log.info("Updating appointment with ID: {}", id);
        AppointmentResponse appointment = appointmentService.updateAppointment(id, appointmentRequest);
        if (appointment != null) {
            log.debug("Updated appointment: {}", appointment);
        }
        log.warn("Failed to update appointment with ID: {}", id);
        return appointment;
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        log.info("Deleting appointment with ID: {}", id);
        try {
            appointmentService.deleteAppointment(id);
            log.debug("Deleted appointment with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting appointment with ID: {}", id, e);
        }
    }

}
