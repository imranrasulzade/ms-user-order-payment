package com.example.userservice.service;

import com.example.userservice.client.AuthClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserRoleCacheDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.enums.Role;
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
    private final AuthClient authClient;

    public Long add(UserDto dto) {
        log.info("Adding user by username: {}", dto.getUsername());
        UserEntity userEntity = userMapper.toEntity(dto);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        Long userId = savedUserEntity.getId();
        log.info("User added by username: {}", userEntity.getUsername());
        return userId;
    }

    public UserDto get(Long id) {
        log.info("Getting user by id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        UserDto userDto = userMapper.toDto(userEntity);
        log.info("User found by id: {}", userDto.getId());
        return userDto;
    }

    public UserRoleCacheDto getRole(Long id) {
        log.info("Getting role by id: {}", id);
        UserDto userDto = get(id);
        UserRoleCacheDto userRoleCacheDto = new UserRoleCacheDto();
        userRoleCacheDto.setRole(userDto.getRole());
        userRoleCacheDto.setId(userDto.getId());
        log.info("Getting role user found by id: {}", userDto.getId());
        return userRoleCacheDto;
    }

    public void changeRole(Long id, Role role) {
        log.info("Changing role by id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        userEntity.setRole(role);
        userRepository.save(userEntity);
        log.info("Evict from cache role by id: {}", id);
        authClient.evictRoleCache(id);
        log.info("Evicted from cache role by id: {}", id);
        log.info("Changing role user found by id: {}", id);
    }
}
