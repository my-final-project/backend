package br.com.juhmaran.pet_flow_cloud.roles.entities;

import br.com.juhmaran.pet_flow_cloud.utils.enums.EnumUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType implements EnumUtils.HasMessageKey {

    ADMIN("role.admin"), // Administrador do Sistema.
    USER("role.user"), // Usuário padrão.
    OWNER("role.owner"), // Proprietário do PetShop.
    EMPLOYEE("role.employee"), // Funcionário do PetShop.
    VETERINARIAN("role.veterinarian"); // Médico Veterinário.

    private final String messageKey;

}
