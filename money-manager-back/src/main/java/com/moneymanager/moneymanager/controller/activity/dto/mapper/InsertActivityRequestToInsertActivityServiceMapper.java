package com.moneymanager.moneymanager.controller.activity.dto.mapper;

import com.moneymanager.moneymanager.controller.activity.dto.InsertActivityRequestDto;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityInputDto;

import java.util.function.Function;

public class InsertActivityRequestToInsertActivityServiceMapper implements Function<InsertActivityRequestDto, InsertActivityInputDto> {
    public static InsertActivityRequestToInsertActivityServiceMapper build() {
        return new InsertActivityRequestToInsertActivityServiceMapper();
    }

    @Override
    public InsertActivityInputDto apply(final InsertActivityRequestDto input) {
        return new InsertActivityInputDto(
                input.date(),
                input.description(),
                input.value(),
                input.type()
        );
    }
}
