package com.moneymanager.moneymanager.controller.activity.dto;

import java.time.Instant;

public record InsertActivityRequestDto(
        Instant date,
        String description,
        float value,
        String type
) {
}
