package br.com.juhmaran.pet_flow_cloud.roles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    ADMIN("Administrador do Sistema."),
    USER("Usuário padrão."),
    OWNER("Proprietário do PetShop."),
    EMPLOYEE("Funcionário do PetShop."),
    VETERINARIAN("Médico Veterinário.");

    private final String description;

}
