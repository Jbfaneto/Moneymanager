package com.moneymanager.moneymanager.service.activity;


import com.moneymanager.moneymanager.domain.dtos.InsertActivityInputDto;
import com.moneymanager.moneymanager.domain.dtos.InsertActivityOutputDto;
import com.moneymanager.moneymanager.domain.dtos.ListActivityOutputDto;

import java.util.List;

public interface ActivityService {
    public InsertActivityOutputDto insert(final InsertActivityInputDto input);
    public void delete(final String id);
    public List<ListActivityOutputDto> listActivities();
    public List<ListActivityOutputDto> listActivitiesByMonth(int month);
    public float calculateBalance();
}
