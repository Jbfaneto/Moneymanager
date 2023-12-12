package com.moneymanager.moneymanager.domain.dtos.mapper;

import com.moneymanager.moneymanager.domain.activity.Activity;
import com.moneymanager.moneymanager.domain.dtos.ListActivityOutputDto;

import java.util.function.Function;

public class ActivityToListActivityOutputDtoMapper implements Function<Activity, ListActivityOutputDto> {
    public static ActivityToListActivityOutputDtoMapper build() {
        return new ActivityToListActivityOutputDtoMapper();
    }
    @Override
    public ListActivityOutputDto apply(final Activity activity) {
        return new ListActivityOutputDto(
                activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getType().toString(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }
}
