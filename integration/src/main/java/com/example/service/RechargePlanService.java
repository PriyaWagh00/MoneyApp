//package com.example.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.entity.RechargePlan;
//import com.example.repository.RechargePlanRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class RechargePlanService {
//
//    @Autowired
//    private RechargePlanRepository rechargePlanRepository;
//
//    public List<RechargePlan> getAllPlans() {
//        return rechargePlanRepository.findAll();
//    }
//
//    public Optional<RechargePlan> getPlanById(Long id) {
//        return rechargePlanRepository.findById(id);
//    }
//
//    public RechargePlan createPlan(RechargePlan rechargePlan) {
//        return rechargePlanRepository.save(rechargePlan);
//    }
//
//    public RechargePlan updatePlan(Long id, RechargePlan rechargePlan) {
//        if (rechargePlanRepository.existsById(id)) {
////            rechargePlan.setId(id);
//            return rechargePlanRepository.save(rechargePlan);
//        } else {
//            return null; // Plan not found
//        }
//    }
//
//    public void deletePlan(Long id) {
//        rechargePlanRepository.deleteById(id);
//    }
//}
