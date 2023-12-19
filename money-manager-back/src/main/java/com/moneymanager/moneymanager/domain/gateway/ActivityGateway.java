package com.moneymanager.moneymanager.domain.gateway;

import com.moneymanager.moneymanager.domain.activity.Activity;

import java.util.List;

public interface ActivityGateway {
    public void create(final Activity activity);
    public void delete(final String id);
    public List<Activity> findAll();
    public Activity findById(final String id);

    public List<Activity> findActivitiesByMonthAndYear(int month, int year);
}
