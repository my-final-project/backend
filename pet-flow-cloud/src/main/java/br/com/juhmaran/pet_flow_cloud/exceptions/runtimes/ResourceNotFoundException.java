package br.com.juhmaran.pet_flow_cloud.exceptions.runtimes;

// Exception para recursos não encontrados (Not Found)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
