package br.com.juhmaran.pet_flow_cloud.users.services;

import br.com.juhmaran.pet_flow_cloud.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}
