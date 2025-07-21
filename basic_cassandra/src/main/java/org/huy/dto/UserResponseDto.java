package org.huy.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID userId;
    private String username;
}
