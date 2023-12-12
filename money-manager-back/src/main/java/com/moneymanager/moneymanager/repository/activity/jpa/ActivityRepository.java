package com.moneymanager.moneymanager.repository.activity.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, String> {

}
