package com.moneymanager.moneymanager.controller.activity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record InsertActivityResponseDto(
        String id,
        Instant date,
        String description,
        float value,
        String type,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt) {
}
