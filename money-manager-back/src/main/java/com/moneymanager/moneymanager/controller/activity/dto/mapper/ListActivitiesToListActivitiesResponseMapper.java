package com.moneymanager.moneymanager.controller.activity.dto.mapper;

import com.moneymanager.moneymanager.controller.activity.dto.ActivityResponseDto;
import com.moneymanager.moneymanager.controller.activity.dto.ListActivityDto;
import com.moneymanager.moneymanager.domain.dtos.ListActivityOutputDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListActivitiesToListActivitiesResponseMapper implements Function<List<ListActivityOutputDto>, ListActivityDto> {
      public static ListActivitiesToListActivitiesResponseMapper build() {
            return new ListActivitiesToListActivitiesResponseMapper();
        }
    @Override
    public ListActivityDto apply(final List<ListActivityOutputDto> input) {
        final var list = input.stream()
                .map(a -> new ActivityResponseDto(
                        a.id(),
                        a.date(),
                        a.description(),
                        a.type(),
                        a.value()))
                .collect(Collectors.toList());
        return new ListActivityDto(list);
    }
}
