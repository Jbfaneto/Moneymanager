package com.moneymanager.moneymanager.domain.activity;

import com.moneymanager.moneymanager.domain.activity.type.Type;
import com.moneymanager.moneymanager.domain.exceptions.DomainException;
import com.moneymanager.moneymanager.utils.InstantUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "activity")
public class Activity {
    @Column(name = "id")
    @Id
    private String id;
    @Column(name = "date")
    private Instant date;
    private String description;
    private Float value;
    private Type type;
    private Instant createdAt;
    private Instant updatedAt;

    private Activity(final String id, final Instant date, final String description, final Float value, final Type type, final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.validate();
    }
    public static Activity newActivity(final Instant date, final String description, final Float value, final Type type, final Instant createdAt, final Instant updatedAt) {
        return new Activity(UUID.randomUUID().toString().toLowerCase(), date, description, value, type, InstantUtils.now(), InstantUtils.now());
    }

    public static Activity with(final String id, final Instant date, final String description, final Float value, final Type type, final Instant createdAt, final Instant updatedAt) {
        return new Activity(id, date, description, value, type, createdAt, updatedAt);
    }

    private void validate(){
        if(this.id.isBlank()){
            throw new DomainException("Id should not be blank");
        }
        else if (this.id.length() != 36){
            throw new DomainException("Id should have 36 characters");
        }
        else if(this.date == null){
            throw new DomainException("Date should not be null");
        }
        else if(this.description.isBlank()){
            throw new DomainException("Description should not be blank");
        }
        else if(this.description.length() < 3){
            throw new DomainException("Description should have at least 3 characters");
        }
        else if(this.value == null){
            throw new DomainException("Value should not be null");
        }
        else if (this.value < 0.01){
            throw new DomainException("Value should be greater than 0.01");
        }
        else if(this.type == null){
            throw new DomainException("Type should not be null");
        }
        else if(this.type != Type.REVENUE && this.type != Type.EXPENSE){
            throw new DomainException("Type should be REVENUE or EXPENSE");
        }
        else if(this.createdAt == null){
            throw new DomainException("CreatedAt should not be null");
        }
        else if(this.updatedAt == null){
            throw new DomainException("UpdatedAt should not be null");
        }
        else if(this.createdAt.isAfter(this.updatedAt)){
            throw new DomainException("CreatedAt should not be after UpdatedAt");
        }
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
