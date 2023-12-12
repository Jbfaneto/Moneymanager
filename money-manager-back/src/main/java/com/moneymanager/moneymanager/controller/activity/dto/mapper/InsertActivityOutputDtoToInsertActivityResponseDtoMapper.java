package com.moneymanager.moneymanager.controller.activity.dto.mapper;

import com.moneymanager.moneymanager.controller.activity.dto.InsertActivityResponseDto;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityOutputDto;

import java.util.function.Function;

public class InsertActivityOutputDtoToInsertActivityResponseDtoMapper implements Function<InsertActivityOutputDto, InsertActivityResponseDto> {
    public static InsertActivityOutputDtoToInsertActivityResponseDtoMapper build() {
        return new InsertActivityOutputDtoToInsertActivityResponseDtoMapper();
    }

    @Override
    public InsertActivityResponseDto apply(final InsertActivityOutputDto input) {
        return new InsertActivityResponseDto(
                input.id(),
                input.date(),
                input.description(),
                input.value(),
                input.type(),
                input.createdAt(),
                input.updatedAt()
        );
    }
}
