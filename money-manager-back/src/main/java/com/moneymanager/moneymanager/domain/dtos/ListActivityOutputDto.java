package com.moneymanager.moneymanager.domain.dtos;

import java.time.Instant;

public record ListActivityOutputDto(
        String id,
        Instant date,
        String description,
        float value,
        String type,
        Instant createdAt,
        Instant updatedAt){
}
