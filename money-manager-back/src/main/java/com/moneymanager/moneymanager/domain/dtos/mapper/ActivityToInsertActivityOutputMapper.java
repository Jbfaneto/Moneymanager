package com.moneymanager.moneymanager.domain.dtos.mapper;

import com.moneymanager.moneymanager.domain.activity.Activity;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityOutputDto;

import java.util.function.Function;

public class ActivityToInsertActivityOutputMapper implements Function<Activity, InsertActivityOutputDto> {
    public static ActivityToInsertActivityOutputMapper build() {
        return new ActivityToInsertActivityOutputMapper();
    }

    @Override
    public InsertActivityOutputDto apply(final Activity activity) {
        return new InsertActivityOutputDto(
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
