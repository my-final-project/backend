package br.com.juhmaran.pet_flow_cloud.service.controllers;

import br.com.juhmaran.pet_flow_cloud.service.dto.ServiceRequest;
import br.com.juhmaran.pet_flow_cloud.service.dto.ServiceResponse;
import br.com.juhmaran.pet_flow_cloud.service.services.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceResponse getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @PostMapping
    public ServiceResponse createService(@RequestBody ServiceRequest serviceRequest) {
        return serviceService.createService(serviceRequest);
    }

    @PutMapping("/{id}")
    public ServiceResponse updateService(@PathVariable Long id,
                                         @RequestBody ServiceRequest serviceRequest) {
        return serviceService.updateService(id, serviceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }

}
