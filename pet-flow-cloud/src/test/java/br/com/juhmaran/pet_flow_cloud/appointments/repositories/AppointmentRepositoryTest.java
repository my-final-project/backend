package br.com.juhmaran.pet_flow_cloud.appointments.repositories;

import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class AppointmentRepositoryTest {

    private Appointment appointment;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
        appointment.setUserId(1L);
        appointment.setPetId(1L);
        appointment.setServiceId(1L);
        appointment.setAppointmentDate(OffsetDateTime.now());
        appointment.setStatus("Scheduled");
    }

    @Test
    @DisplayName("test given Appointment object when save then return saved Appointment")
    void testGivenAppointmentObject_whenSave_thenReturnSavedAppointment() {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        assertNotNull(savedAppointment);
        assertTrue(savedAppointment.getId() > 0);
    }

    @Test
    @DisplayName("test when findAll then return list of Appointments")
    void testWhenFindAll_thenReturnListOfAppointments() {
        appointmentRepository.save(appointment);
        List<Appointment> appointments = appointmentRepository.findAll();
        assertNotNull(appointments);
        assertTrue(appointments.size() > 0);

    }

    @Test
    @DisplayName("test given Appointment ID when findById then return Appointment")
    void testGivenAppointmentId_whenFindById_thenReturnAppointment() {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        Optional<Appointment> foundAppointment = appointmentRepository.findById(savedAppointment.getId());
        assertTrue(foundAppointment.isPresent());
        assertEquals(savedAppointment.getId(), foundAppointment.get().getId());
    }

    @Test
    @DisplayName("test given Appointment object when update then return updated Appointment")
    void testGivenAppointmentObject_whenUpdate_thenReturnUpdatedAppointment() {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        savedAppointment.setStatus("Completed");
        Appointment updatedAppointment = appointmentRepository.save(savedAppointment);
        assertNotNull(updatedAppointment);
        assertEquals("Completed", updatedAppointment.getStatus());
    }

    @Test
    @DisplayName("test given Appointment ID when deleteById then remove Appointment")
    void testGivenAppointmentId_whenDeleteById_thenRemoveAppointment() {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        appointmentRepository.deleteById(savedAppointment.getId());
        Optional<Appointment> deletedAppointment = appointmentRepository.findById(savedAppointment.getId());
        assertFalse(deletedAppointment.isPresent());
    }

}