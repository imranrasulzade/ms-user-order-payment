package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void add(UserDto dto) {
        log.info("Adding user by username: {}", dto.getUsername());
        UserEntity userEntity = userMapper.toEntity(dto);
        userRepository.save(userEntity);
        log.info("User added by username: {}", userEntity.getUsername());
    }

    public UserDto get(Long id) {
        log.info("Getting user by id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        UserDto userDto = userMapper.toDto(userEntity);
        log.info("User found by id: {}", userDto.getId());
        return userDto;
    }
}
