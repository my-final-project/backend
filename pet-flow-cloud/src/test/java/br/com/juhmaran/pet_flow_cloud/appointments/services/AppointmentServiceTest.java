package br.com.juhmaran.pet_flow_cloud.appointments.services;

import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentRequest;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentResponse;
import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import br.com.juhmaran.pet_flow_cloud.appointments.mapping.AppointmentMapper;
import br.com.juhmaran.pet_flow_cloud.appointments.repositories.AppointmentRepository;
import br.com.juhmaran.pet_flow_cloud.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService service;

    @Mock
    private AppointmentMapper mapper;

    @Mock
    private AppointmentRepository repository;

    Appointment appointment;
    AppointmentRequest appointmentRequest;
    AppointmentResponse appointmentResponse;
    List<Appointment> expectedAppointments;

    @BeforeEach
    void setUp() {

        service = new AppointmentService(mapper, repository);
        appointment = new Appointment(1L, 1L, 1L, 1L, OffsetDateTime.now(),
                "Scheduled", OffsetDateTime.now(), OffsetDateTime.now());
        appointmentRequest = new AppointmentRequest(1L, 1L, 1L, OffsetDateTime.now(),
                "Scheduled");
        appointmentResponse = new AppointmentResponse(1L, 1L, 1L, 1L, OffsetDateTime.now(),
                "Scheduled", OffsetDateTime.now(), OffsetDateTime.now());
        expectedAppointments = List.of(appointment);

    }

    @Test
    @DisplayName("test getAllAppointments when called then return list of AppointmentResponses")
    void testGetAllAppointments_whenCalled_thenReturnListOfAppointmentResponses() {
        // Given
        when(repository.findAll()).thenReturn(expectedAppointments);
        when(mapper.toResponseList(expectedAppointments)).thenReturn(List.of(appointmentResponse));

        // When
        List<AppointmentResponse> actualAppointments = service.getAllAppointments();

        // Then
        assertNotNull(actualAppointments);
        assertEquals(1, actualAppointments.size());
        assertEquals(appointmentResponse, actualAppointments.getFirst());
    }

    @Test
    @DisplayName("test getAppointmentById when called with valid ID then return AppointmentResponse")
    void testGetAppointmentById_whenCalledWithValidId_thenReturnAppointmentResponse() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(appointment));
        when(mapper.toResponse(appointment)).thenReturn(appointmentResponse);

        // When
        AppointmentResponse actualAppointment = service.getAppointmentById(1L);

        // Then
        assertNotNull(actualAppointment);
        assertEquals(appointmentResponse, actualAppointment);
    }

    @Test
    @DisplayName("test getAppointmentById when called with invalid ID then throw AppointmentNotFoundException")
    void testGetAppointmentById_whenCalledWithInvalidId_thenThrowAppointmentNotFoundException() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.getAppointmentById(1L));
    }

    @Test
    @DisplayName("test createAppointment when called then return created AppointmentResponse")
    void testCreateAppointment_whenCalled_thenReturnCreatedAppointmentResponse() {
        // Given
        when(mapper.toEntity(appointmentRequest)).thenReturn(appointment);
        when(repository.save(appointment)).thenReturn(appointment);
        when(mapper.toResponse(appointment)).thenReturn(appointmentResponse);

        // When
        AppointmentResponse actualAppointment = service.createAppointment(appointmentRequest);

        // Then
        assertNotNull(actualAppointment);
        assertEquals(appointmentResponse, actualAppointment);
    }

    @Test
    @DisplayName("test updateAppointment when called with valid ID then return updated AppointmentResponse")
    void testUpdateAppointment_whenCalledWithValidId_thenReturnUpdatedAppointmentResponse() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(appointment));
        when(repository.save(appointment)).thenReturn(appointment);
        when(mapper.toResponse(appointment)).thenReturn(appointmentResponse);

        // When
        AppointmentResponse actualAppointment = service.updateAppointment(1L, appointmentRequest);

        // Then
        assertNotNull(actualAppointment);
        assertEquals(appointmentResponse, actualAppointment);
    }

    @Test
    @DisplayName("test updateAppointment when called with invalid ID then throw AppointmentNotFoundException")
    void testUpdateAppointment_whenCalledWithInvalidId_thenThrowAppointmentNotFoundException() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.updateAppointment(1L, appointmentRequest));
    }

    @Test
    @DisplayName("test deleteAppointment when called with valid ID then delete Appointment")
    void testDeleteAppointment_whenCalledWithValidId_thenDeleteAppointment() {
        // Given
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        // When
        service.deleteAppointment(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("test deleteAppointment when called with invalid ID then throw AppointmentNotFoundException")
    void testDeleteAppointment_whenCalledWithInvalidId_thenThrowAppointmentNotFoundException() {
        // Given
        when(repository.existsById(1L)).thenReturn(false);

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.deleteAppointment(1L));
    }

}