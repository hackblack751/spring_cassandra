package org.huy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @PrimaryKey
    @Column("user_id")
    private UUID userId;

    @Column("username")
    private String username;

    @Column("created_at")
    private Instant createdAt;
}
