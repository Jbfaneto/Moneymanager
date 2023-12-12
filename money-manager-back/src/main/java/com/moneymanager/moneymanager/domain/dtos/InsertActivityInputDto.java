package com.moneymanager.moneymanager.domain.dtos;

import java.time.Instant;

public record InsertActivityInputDto(
        Instant date,
        String description,
        float value,
        String type
) {
}
