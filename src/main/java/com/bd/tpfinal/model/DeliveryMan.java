package com.bd.tpfinal.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Calendar;
import java.util.Date;

@Document
public class DeliveryMan extends User{

    @Field
    private int numberOfSuccess;

    @Field
    private boolean free;

    @Field
    private Date dateOfAdmission;

    public DeliveryMan(){}

    public DeliveryMan(String name, String email, String username, String password, Date dateOfBirth) {
        super(name, email, username, password, dateOfBirth);
        this.numberOfSuccess = 0;
        this.free = true;
        this.dateOfAdmission = Calendar.getInstance().getTime();
    }
    public int getNumberOfSuccess() {
      return numberOfSuccess;
    }

    public void setNumberOfSuccess(int numberOfSuccess) {
        this.numberOfSuccess = numberOfSuccess;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public void addNumberOfSuccess(){ this.numberOfSuccess++; }

}
