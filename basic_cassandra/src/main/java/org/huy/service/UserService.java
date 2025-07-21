package org.huy.service;

import lombok.RequiredArgsConstructor;
import org.huy.dto.UserDto;
import org.huy.dto.UserResponseDto;
import org.huy.entity.User;
import org.huy.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserResponseDto findById(UUID userId) {
        Optional<User> result = this.userRepository.findById(userId);
        UserResponseDto response = new UserResponseDto();
        if(result.isPresent()) {
            User user = result.get();
            response.setUserId(user.getUserId());
            response.setUsername(user.getUsername());
        }

        return response;
    }


    public List<UserDto> getAllUsers() {
        List<UserDto> users = this.userRepository.findAll().stream()
                  .map(u -> this.modelMapper.map(u, UserDto.class))
                  .toList();

        return users;
    }


}
