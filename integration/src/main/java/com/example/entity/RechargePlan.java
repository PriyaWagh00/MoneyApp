package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RechargePlan {

    @Id
    private int planId;

    private String planName;
    private double price;

    // Constructors, getters, and setters

    public RechargePlan() {}

    public RechargePlan(int planId, String planName, double price) {
        this.planId = planId;
        this.planName = planName;
        this.price = price;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
