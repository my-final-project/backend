package br.com.juhmaran.pet_flow_cloud.appointments.controllers;

import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentRequest;
import br.com.juhmaran.pet_flow_cloud.appointments.dto.AppointmentResponse;
import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import br.com.juhmaran.pet_flow_cloud.appointments.services.AppointmentService;
import br.com.juhmaran.pet_flow_cloud.consultation.services.ConsultationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.OffsetDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private ConsultationService consultationService; // Adicione isso para resolver a dependência

    Appointment appointment;
    private AppointmentRequest appointmentRequest;
    private AppointmentResponse appointmentResponse;
    private List<AppointmentResponse> appointmentResponseList;

    @BeforeEach
    void setUp() {
        appointment = new Appointment(1L, 1L, 1L, 1L, OffsetDateTime.now(), "Scheduled", OffsetDateTime.now(), OffsetDateTime.now());
        appointmentRequest = new AppointmentRequest(1L, 1L, 1L, OffsetDateTime.now(), "Scheduled");
        appointmentResponse = new AppointmentResponse(1L, 1L, 1L, 1L, OffsetDateTime.now(), "Scheduled", OffsetDateTime.now(), OffsetDateTime.now());
        appointmentResponseList = List.of(appointmentResponse);
    }

    //@Test
    @DisplayName("test getAllAppointments when called then return list of AppointmentResponses")
    void testGetAllAppointments_whenCalled_thenReturnListOfAppointmentResponses() throws Exception {
        // Given
        given(appointmentService.getAllAppointments()).willReturn(appointmentResponseList);

        // When
        ResultActions response = mockMvc.perform(get("/appointments")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(appointmentResponseList.size())));
    }

    //@Test
    @DisplayName("test getAppointmentById when called with valid ID then return AppointmentResponse")
    void testGetAppointmentById_whenCalledWithValidId_thenReturnAppointmentResponse() throws Exception {
        // Given
        given(appointmentService.getAppointmentById(1L)).willReturn(appointmentResponse);

        // When
        ResultActions response = mockMvc.perform(get("/appointments/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(appointmentResponse.getId().intValue())))
                .andExpect(jsonPath("$.status", is(appointmentResponse.getStatus())));
    }

    //@Test
    @DisplayName("test createAppointment when called then return created AppointmentResponse")
    void testCreateAppointment_whenCalled_thenReturnCreatedAppointmentResponse() throws Exception {
        // Given
        given(appointmentService.createAppointment(any(AppointmentRequest.class))).willReturn(appointmentResponse);

        // When
        ResultActions response = mockMvc.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(appointmentRequest)));

        // Then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(appointmentResponse.getId().intValue())))
                .andExpect(jsonPath("$.status", is(appointmentResponse.getStatus())));
    }

    //@Test
    @DisplayName("test updateAppointment when called with valid ID then return updated AppointmentResponse")
    void testUpdateAppointment_whenCalledWithValidId_thenReturnUpdatedAppointmentResponse() throws Exception {
        // Given
        given(appointmentService.updateAppointment(any(Long.class), any(AppointmentRequest.class))).willReturn(appointmentResponse);

        // When
        ResultActions response = mockMvc.perform(put("/appointments/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(appointmentRequest)));

        // Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(appointmentResponse.getId().intValue())))
                .andExpect(jsonPath("$.status", is(appointmentResponse.getStatus())));
    }

    //@Test
    @DisplayName("test deleteAppointment when called with valid ID then return no content")
    void testDeleteAppointment_whenCalledWithValidId_thenReturnNoContent() throws Exception {
        // Given
        willDoNothing().given(appointmentService).deleteAppointment(1L);

        // When
        ResultActions response = mockMvc.perform(delete("/appointments/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(status().isNoContent())
                .andDo(print());
    }

}