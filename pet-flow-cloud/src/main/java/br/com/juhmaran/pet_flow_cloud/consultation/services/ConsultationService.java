package br.com.juhmaran.pet_flow_cloud.consultation.services;

import br.com.juhmaran.pet_flow_cloud.consultation.dto.ConsultationRequest;
import br.com.juhmaran.pet_flow_cloud.consultation.dto.ConsultationResponse;
import br.com.juhmaran.pet_flow_cloud.consultation.entities.Consultation;
import br.com.juhmaran.pet_flow_cloud.consultation.mapping.ConsultationMapper;
import br.com.juhmaran.pet_flow_cloud.consultation.repositories.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationMapper consultationMapper;
    private final ConsultationRepository consultationRepository;

    public List<ConsultationResponse> getAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultationMapper.toResponseList(consultations);
    }

    public ConsultationResponse getConsultationById(Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        return consultation.map(consultationMapper::toResponse).orElse(null);
    }

    public ConsultationResponse createConsultation(ConsultationRequest consultationRequest) {
        Consultation consultation = consultationMapper.toEntity(consultationRequest);
        consultation = consultationRepository.save(consultation);
        return consultationMapper.toResponse(consultation);
    }

    public ConsultationResponse updateConsultation(Long id, ConsultationRequest consultationRequest) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);
        if (optionalConsultation.isPresent()) {
            Consultation consultation = optionalConsultation.get();
            consultation.setPetId(consultationRequest.getPetId());
            consultation.setVeterinarianId(consultationRequest.getVeterinarianId());
            consultation.setConsultationDate(consultationRequest.getConsultationDate());
            consultation.setNotes(consultationRequest.getNotes());
            consultation = consultationRepository.save(consultation);
            return consultationMapper.toResponse(consultation);
        }
        return null;
    }

    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

}
