package br.com.juhmaran.pet_flow_cloud.users.mapping;

import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.response.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequest userRequest);

    @Mapping(target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "lastModifiedDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToRoleNames")
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @Named("rolesToRoleNames")
    default Set<String> rolesToRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }

}
