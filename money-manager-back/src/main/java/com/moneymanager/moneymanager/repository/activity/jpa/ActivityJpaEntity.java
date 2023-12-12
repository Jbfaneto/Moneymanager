package com.moneymanager.moneymanager.repository.activity.jpa;

import com.moneymanager.moneymanager.domain.activity.Activity;
import com.moneymanager.moneymanager.domain.activity.type.Type;
import com.moneymanager.moneymanager.domain.exceptions.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.List;

@Entity(name = "activity")
@Table(name = "activities")
public class ActivityJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private Instant date;
    @Column(name = "value", nullable = false)
    private Float value;
    @Column(name = "type", nullable = false)
    private Integer type;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
    public ActivityJpaEntity() {
    }
    private ActivityJpaEntity(final String id, final Instant date, final String description, final Float value, final Integer type, final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ActivityJpaEntity from (Activity activity){
        final var type = List.of(Type.values()).stream().filter(a -> a.equals(activity.getType())).findFirst().get().ordinal();
        if (type != 0 && type != 1) {
            throw new DomainException("Type should be revenue or expense");
        }
        return new ActivityJpaEntity(activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                type,
                activity.getCreatedAt(),
                activity.getUpdatedAt());
    }

    public Activity toModel(){
        return Activity.with(this.id,
                this.date,
                this.description,
                this.value,
                Type.values()[this.type],
                this.createdAt,
                this.updatedAt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
