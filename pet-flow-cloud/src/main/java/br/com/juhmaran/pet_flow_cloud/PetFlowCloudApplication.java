package br.com.juhmaran.pet_flow_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PetFlowCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetFlowCloudApplication.class, args);
    }

}
