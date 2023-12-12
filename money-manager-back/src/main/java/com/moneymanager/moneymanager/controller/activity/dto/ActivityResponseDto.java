package com.moneymanager.moneymanager.controller.activity.dto;

import java.time.Instant;

public record ActivityResponseDto(
        String id,
        Instant date,
        String description,
        String type,
        Float value
) {
}
