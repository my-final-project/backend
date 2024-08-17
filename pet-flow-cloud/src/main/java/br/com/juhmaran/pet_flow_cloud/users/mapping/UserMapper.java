package br.com.juhmaran.pet_flow_cloud.users.mapping;

import br.com.juhmaran.pet_flow_cloud.users.dto.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    User toEntity(UserRequest userRequest);

    @Mapping(target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "lastModifiedDate", dateFormat = "yyyy-MM-dd HH:mm")
    UserResponse toResponse(User pet);

    List<UserResponse> toResponseList(List<User> pets);

}
