package com.moneymanager.moneymanager.service.activity.implementation;

import com.moneymanager.moneymanager.domain.activity.type.Type;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityInputDto;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityOutputDto;
import com.moneymanager.moneymanager.domain.dtos.ListActivityOutputDto;
import com.moneymanager.moneymanager.domain.dtos.mapper.ActivityToInsertActivityOutputMapper;
import com.moneymanager.moneymanager.domain.dtos.mapper.ActivityToListActivityOutputDtoMapper;
import com.moneymanager.moneymanager.domain.dtos.mapper.InsertActivityInputToActivityMapper;
import com.moneymanager.moneymanager.domain.gateway.ActivityGateway;
import com.moneymanager.moneymanager.service.activity.ActivityService;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityServiceImplemented implements ActivityService {
    private final ActivityGateway activityGateway;

    private ActivityServiceImplemented(ActivityGateway activityGateway) {
        this.activityGateway = activityGateway;
    }

    public static ActivityServiceImplemented build(ActivityGateway activityGateway) {
        return new ActivityServiceImplemented(activityGateway);
    }


    @Override
    public InsertActivityOutputDto insert(final InsertActivityInputDto input) {
        final var activity = InsertActivityInputToActivityMapper.build().apply(input);
        this.activityGateway.create(activity);
        return ActivityToInsertActivityOutputMapper.build().apply(activity);
    }

    @Override
    public void delete(final String id) {
        this.activityGateway.delete(id);
    }

    @Override
    public List<ListActivityOutputDto> listActivities() {
        final var list = this.activityGateway.findAll();
        return list.stream()
                .map(activity -> ActivityToListActivityOutputDtoMapper.build().apply(activity)).collect(Collectors.toList());
    }

    @Override
    public List<ListActivityOutputDto> listActivitiesByMonth(int month) {
        final var list = this.activityGateway.findActivitiesByMonth(month);
        return list.stream()
                .map(activity -> ActivityToListActivityOutputDtoMapper.build().apply(activity)).collect(Collectors.toList());
    }

    @Override
    public float calculateBalance() {
        final var list = this.activityGateway.findAll();
        if (list.isEmpty()) {
            return 0f;
        }
        return (float) list.stream()
                .mapToDouble(
                        activity -> activity.getType() == Type.REVENUE ? activity.getValue() : -activity.getValue())
                .sum();
    }

}
