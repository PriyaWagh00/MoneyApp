//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.entity.RechargePlan;
//import com.example.service.RechargePlanService;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/plans")
//public class RechargePlanController {
//
//    @Autowired
//    private RechargePlanService rechargePlanService;
//
//    @GetMapping
//    public List<RechargePlan> getAllPlans() {
//        return rechargePlanService.getAllPlans();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<RechargePlan> getPlanById(@PathVariable Long id) {
//        Optional<RechargePlan> plan = rechargePlanService.getPlanById(id);
//        return plan.map(ResponseEntity::ok)
//                   .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<RechargePlan> createPlan(@RequestBody RechargePlan rechargePlan) {
//        RechargePlan createdPlan = rechargePlanService.createPlan(rechargePlan);
//        return ResponseEntity.ok(createdPlan);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<RechargePlan> updatePlan(@PathVariable Long id, @RequestBody RechargePlan rechargePlan) {
//        RechargePlan updatedPlan = rechargePlanService.updatePlan(id, rechargePlan);
//        return updatedPlan != null ? ResponseEntity.ok(updatedPlan) : ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
//        rechargePlanService.deletePlan(id);
//        return ResponseEntity.noContent().build();
//    }
//}
