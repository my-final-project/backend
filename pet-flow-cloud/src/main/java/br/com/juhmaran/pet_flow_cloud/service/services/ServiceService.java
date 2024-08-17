package br.com.juhmaran.pet_flow_cloud.service.services;

import br.com.juhmaran.pet_flow_cloud.service.dto.ServiceRequest;
import br.com.juhmaran.pet_flow_cloud.service.dto.ServiceResponse;
import br.com.juhmaran.pet_flow_cloud.service.entities.Services;
import br.com.juhmaran.pet_flow_cloud.service.mapping.ServiceMapper;
import br.com.juhmaran.pet_flow_cloud.service.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;

    public List<ServiceResponse> getAllServices() {
        List<Services> services = serviceRepository.findAll();
        return serviceMapper.toResponseList(services);
    }

    public ServiceResponse getServiceById(Long id) {
        Optional<Services> service = serviceRepository.findById(id);
        return service.map(serviceMapper::toResponse).orElse(null);
    }

    public ServiceResponse createService(ServiceRequest serviceRequest) {
        Services services = serviceMapper.toEntity(serviceRequest);
        services = serviceRepository.save(services);
        return serviceMapper.toResponse(services);
    }

    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        Optional<Services> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            Services services = optionalService.get();
            services.setName(serviceRequest.getName());
            services.setPrice(serviceRequest.getPrice());
            services.setDescription(serviceRequest.getDescription());
            services = serviceRepository.save(services);
            return serviceMapper.toResponse(services);
        }
        return null;
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

}
