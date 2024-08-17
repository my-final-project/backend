package br.com.juhmaran.pet_flow_cloud.consultation.controllers;

import br.com.juhmaran.pet_flow_cloud.consultation.dto.ConsultationRequest;
import br.com.juhmaran.pet_flow_cloud.consultation.dto.ConsultationResponse;
import br.com.juhmaran.pet_flow_cloud.consultation.services.ConsultationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationResponse> getAllConsultations() {
        return consultationService.getAllConsultations();
    }

    @GetMapping("/{id}")
    public ConsultationResponse getConsultationById(@PathVariable Long id) {
        return consultationService.getConsultationById(id);
    }

    @PostMapping
    public ConsultationResponse createConsultation(@RequestBody ConsultationRequest consultationRequest) {
        return consultationService.createConsultation(consultationRequest);
    }

    @PutMapping("/{id}")
    public ConsultationResponse updateConsultation(@PathVariable Long id, @RequestBody ConsultationRequest consultationRequest) {
        return consultationService.updateConsultation(id, consultationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
    }

}
