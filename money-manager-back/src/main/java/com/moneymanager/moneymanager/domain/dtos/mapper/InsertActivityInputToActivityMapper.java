package com.moneymanager.moneymanager.domain.dtos.mapper;

import com.moneymanager.moneymanager.domain.activity.Activity;
import com.moneymanager.moneymanager.domain.activity.type.Type;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityInputDto;
import com.moneymanager.moneymanager.service.exceptions.ServiceException;
import com.moneymanager.moneymanager.utils.InstantUtils;

import java.util.function.Function;

public class InsertActivityInputToActivityMapper implements Function<InsertActivityInputDto, Activity> {
    public static InsertActivityInputToActivityMapper build() {
        return new InsertActivityInputToActivityMapper();
    }


    @Override
    public Activity apply(final InsertActivityInputDto input) {
        if (input.type().trim().toUpperCase().equals(Type.REVENUE.toString())) {
            final var activity = Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    Type.REVENUE,
                    InstantUtils.now(),
                    InstantUtils.now()
            );
            return activity;
        }
        else if (input.type().trim().toUpperCase().equals(Type.EXPENSE.toString())) {
            final var activity = Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    Type.EXPENSE,
                    InstantUtils.now(),
                    InstantUtils.now()
            );
            return activity;
        } else{
            throw new ServiceException("Invalid type");
        }
    }
}
