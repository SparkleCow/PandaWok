package com.sparklecow.pandawok.user.mapper;

import com.sparklecow.pandawok.user.entity.Role;
import com.sparklecow.pandawok.user.entity.User;
import com.sparklecow.pandawok.user.model.RoleEnum;
import com.sparklecow.pandawok.user.model.UserRequestDto;
import com.sparklecow.pandawok.user.model.UserResponseDto;
import com.sparklecow.pandawok.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .username(dto.username())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .roles(Set.of(getDefaultUserRole()))
                .build();
    }

    public UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getRoles().stream()
                        .map(role -> role.getRoleEnum().name())
                        .collect(Collectors.toSet()));
    }

    private Role getDefaultUserRole() {
        return roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Default role USER not found"));
    }
}
