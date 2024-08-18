package br.com.juhmaran.pet_flow_cloud.users.entities;

import br.com.juhmaran.pet_flow_cloud.utils.enums.EnumUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus implements EnumUtils.HasMessageKey {

    ACTIVE("status.active"),
    INACTIVE("status.inactive"),
    SUSPENDED("status.suspended");

    private final String messageKey;

}
