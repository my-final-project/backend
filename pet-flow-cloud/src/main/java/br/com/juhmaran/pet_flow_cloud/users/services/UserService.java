package br.com.juhmaran.pet_flow_cloud.users.services;

import br.com.juhmaran.pet_flow_cloud.users.dto.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import br.com.juhmaran.pet_flow_cloud.users.mapping.UserMapper;
import br.com.juhmaran.pet_flow_cloud.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseList(users);
    }

    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toResponse).orElse(null);
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userRequest.getName());
            user.setCpf(userRequest.getCpf());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user = userRepository.save(user);
            return userMapper.toResponse(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
