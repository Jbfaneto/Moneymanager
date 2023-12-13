package com.moneymanager.moneymanager.controller.activity;

import com.moneymanager.moneymanager.controller.activity.dto.CalculateBalanceResponseDto;
import com.moneymanager.moneymanager.controller.activity.dto.InsertActivityRequestDto;
import com.moneymanager.moneymanager.controller.activity.dto.InsertActivityResponseDto;
import com.moneymanager.moneymanager.controller.activity.dto.ListActivityDto;
import com.moneymanager.moneymanager.controller.activity.dto.mapper.InsertActivityOutputDtoToInsertActivityResponseDtoMapper;
import com.moneymanager.moneymanager.controller.activity.dto.mapper.InsertActivityRequestToInsertActivityServiceMapper;
import com.moneymanager.moneymanager.controller.activity.dto.mapper.ListActivitiesToListActivitiesResponseMapper;
import com.moneymanager.moneymanager.repository.activity.jpa.ActivityJpaGateway;
import com.moneymanager.moneymanager.repository.activity.jpa.ActivityRepository;
import com.moneymanager.moneymanager.service.activity.implementation.ActivityServiceImplemented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityRepository activityJpaRepository;


    @GetMapping
    public ResponseEntity<ListActivityDto> listActivities(){
        final var gateway = ActivityJpaGateway.build(activityJpaRepository);
        final var service = ActivityServiceImplemented.build(gateway);
        final var list = service.listActivities();
        final var response = ListActivitiesToListActivitiesResponseMapper.build().apply(list);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<InsertActivityResponseDto> insertActivity(@RequestBody InsertActivityRequestDto input){
        final var gateway = ActivityJpaGateway.build(activityJpaRepository);
        final var service = ActivityServiceImplemented.build(gateway);
        final var serviceInput = InsertActivityRequestToInsertActivityServiceMapper.build().apply(input);

        final var serviceResponse = service.insert(serviceInput);
        final var response = InsertActivityOutputDtoToInsertActivityResponseDtoMapper.build().apply(serviceResponse);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable final String id){
        final var gateway = ActivityJpaGateway.build(activityJpaRepository);
        final var service = ActivityServiceImplemented.build(gateway);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<CalculateBalanceResponseDto> calculateBalance(){
        final var gateway = ActivityJpaGateway.build(activityJpaRepository);
        final var service = ActivityServiceImplemented.build(gateway);
        final var serviceResponse = service.calculateBalance();
        final var response = new CalculateBalanceResponseDto(serviceResponse);
        return ResponseEntity.ok().body(response);
    }

}
