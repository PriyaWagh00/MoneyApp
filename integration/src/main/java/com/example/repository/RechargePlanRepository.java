package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.RechargePlan;

public interface RechargePlanRepository extends JpaRepository<RechargePlan, Integer> {
}