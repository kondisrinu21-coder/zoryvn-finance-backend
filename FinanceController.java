package com.zorvyn.controller;
import com.zorvyn.model.FinancialRecord;
import com.zorvyn.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController @RequestMapping("/api/finance")
public class FinanceController {
    @Autowired FinanceService financeService;
    @PostMapping("/records") 
    public ResponseEntity<FinancialRecord> create(@RequestBody FinancialRecord record,
        @RequestHeader("X-User-Email") String userEmail){
        return ResponseEntity.ok(financeService.createRecord(record,userEmail));
    }
    @GetMapping("/records")
    public ResponseEntity<List<FinancialRecord>> getRecords(@RequestHeader("X-User-Email") String userEmail,
        @RequestParam(required=false) String category,@RequestParam(required=false) String type){
        return ResponseEntity.ok(financeService.getRecords(userEmail,category,type));
    }
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String,Object>> dashboard(@RequestHeader("X-User-Email") String userEmail){
        return ResponseEntity.ok(financeService.getDashboard(userEmail));
    }
}
