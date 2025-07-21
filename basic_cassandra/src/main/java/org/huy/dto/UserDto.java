package org.huy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {

    private UUID userId;

    private String username;

    private LocalDateTime createdAt;
}
