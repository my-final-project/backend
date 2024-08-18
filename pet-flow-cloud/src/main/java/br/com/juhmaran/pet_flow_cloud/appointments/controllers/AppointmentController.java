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
        log.info("### [AppointmentController] - Fetching all appointments");
        List<AppointmentResponse> appointments = appointmentService.getAllAppointments();
        log.debug("### [AppointmentController] - Fetched {} appointments", appointments.size());
        return appointments;
    }

    @GetMapping("/{id}")
    public AppointmentResponse getAppointmentById(@PathVariable Long id) {
        log.info("### [AppointmentController] - Fetching appointment with ID: {}", id);
        AppointmentResponse appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            log.debug("### [AppointmentController] - Fetched appointment: {}", appointment);
        }
        log.warn("### [AppointmentController] - Appointment with ID: {} not found", id);
        return appointment;
    }

    @PostMapping
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        log.info("### [AppointmentController] - Creating new appointment");
        AppointmentResponse appointment = appointmentService.createAppointment(appointmentRequest);
        log.debug("### [AppointmentController] - Created appointment: {}", appointment);
        return appointment;
    }

    @PutMapping("/{id}")
    public AppointmentResponse updateAppointment(@PathVariable Long id,
                                                 @RequestBody AppointmentRequest appointmentRequest) {
        log.info("### [AppointmentController] - Updating appointment with ID: {}", id);
        AppointmentResponse appointment = appointmentService.updateAppointment(id, appointmentRequest);
        if (appointment != null) {
            log.debug("### [AppointmentController] - Updated appointment: {}", appointment);
        }
        log.warn("### [AppointmentController] - Failed to update appointment with ID: {}", id);
        return appointment;
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        log.info("### [AppointmentController] - Deleting appointment with ID: {}", id);
        try {
            appointmentService.deleteAppointment(id);
            log.debug("### [AppointmentController] - Deleted appointment with ID: {}", id);
        } catch (Exception e) {
            log.error("### [AppointmentController] - Error deleting appointment with ID: {}", id, e);
        }
    }

}
