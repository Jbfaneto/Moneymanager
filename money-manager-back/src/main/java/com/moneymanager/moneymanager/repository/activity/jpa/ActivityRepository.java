package com.moneymanager.moneymanager.repository.activity.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, String> {
    @Query(value = "SELECT * FROM activities a WHERE MONTH(DATE(a.date)) = :month AND YEAR(DATE(a.date)) = :year", nativeQuery = true)
    List<ActivityJpaEntity> findActivitiesByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
