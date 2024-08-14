package br.com.juhmaran.pet_flow_cloud.users.mapping;

import br.com.juhmaran.pet_flow_cloud.users.dto.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);

    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "id", target = "id")
    UserResponse toUserResponse(User user);

}
