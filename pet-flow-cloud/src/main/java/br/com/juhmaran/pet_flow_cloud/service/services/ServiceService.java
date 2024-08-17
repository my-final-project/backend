package br.com.juhmaran.pet_flow_cloud.service;

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
        List<Service> services = serviceRepository.findAll();
        return serviceMapper.toResponseList(services);
    }

    public ServiceResponse getServiceById(Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.map(serviceMapper::toResponse).orElse(null);
    }

    public ServiceResponse createService(ServiceRequest serviceRequest) {
        Service service = serviceMapper.toEntity(serviceRequest);
        service = serviceRepository.save(service);
        return serviceMapper.toResponse(service);
    }

    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        Optional<Service> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            Service service = optionalService.get();
            service.setName(serviceRequest.getName());
            service.setPrice(serviceRequest.getPrice());
            service.setDescription(serviceRequest.getDescription());
            service = serviceRepository.save(service);
            return serviceMapper.toResponse(service);
        }
        return null;
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

}
