package com.moneymanager.moneymanager.repository.activity.jpa;

import com.moneymanager.moneymanager.domain.activity.Activity;
import com.moneymanager.moneymanager.domain.gateway.ActivityGateway;
import com.moneymanager.moneymanager.repository.exceptions.EntityNotFoundException;
import com.moneymanager.moneymanager.repository.exceptions.PersistenceExcetion;
import org.springframework.dao.OptimisticLockingFailureException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActivityJpaGateway implements ActivityGateway {
    private final ActivityRepository activityRepository;

    private ActivityJpaGateway(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    public static ActivityJpaGateway build(ActivityRepository activityRepository) {
        return new ActivityJpaGateway(activityRepository);
    }

    @Override
    public void create(Activity activity) {
        final var activityEntity = ActivityJpaEntity.from(activity);
        try {
            this.activityRepository.save(activityEntity);
        } catch (IllegalArgumentException e) {
            throw new PersistenceExcetion(e.getMessage());
        } catch (OptimisticLockingFailureException e) {
            throw new PersistenceExcetion(e.getMessage());
        }
    }

    @Override
    public void delete(final String id) {

        this.findById(id);
        this.activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> findAll() {
        final var list = this.activityRepository.findAll();
        return list.stream().map(activityEntity -> activityEntity.toModel()).collect(Collectors.toList());
    }

    @Override
    public Activity findById(String id) {
        Optional<ActivityJpaEntity> activity = this.activityRepository.findById(id);
        return activity.orElseThrow(() -> new EntityNotFoundException("Id not found")).toModel();
    }

    public List<Activity> findActivitiesByMonthAndYear(int month, int year) {
        final var list = this.activityRepository.findActivitiesByMonthAndYear(month, year);
        return list.stream().map(activityEntity -> activityEntity.toModel()).collect(Collectors.toList());
    }

}
