package com.zorvyn.service;
import com.zorvyn.model.FinancialRecord;
import com.zorvyn.model.User;
import com.zorvyn.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class FinanceService {
    @Autowired FinanceRepository financeRepo;
    @Autowired UserService userService;
    public FinancialRecord createRecord(FinancialRecord record,String userEmail){
        User user=userService.findByEmail(userEmail);
        if(!hasPermission(user,"CREATE")) throw new SecurityException("Access denied");
        record.setUserId(user.getId());
        return financeRepo.save(record);
    }
    public List<FinancialRecord> getRecords(String userEmail,String category,String type){
        User user=userService.findByEmail(userEmail);
        if(!hasPermission(user,"READ")) throw new SecurityException("Access denied");
        return financeRepo.findFiltered(user.getId(),category,type);
    }
    public Map<String,Object> getDashboard(String userEmail){
        User user=userService.findByEmail(userEmail);
        if(!hasPermission(user,"READ")) throw new SecurityException("Access denied");
        List<FinancialRecord> records=financeRepo.findByUserId(user.getId());
        BigDecimal income=records.stream().filter(r->"INCOME".equals(r.getType()))
            .map(FinancialRecord::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal expense=records.stream().filter(r->"EXPENSE".equals(r.getType()))
            .map(FinancialRecord::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        return Map.of("totalIncome",income,"totalExpense",expense,
            "netBalance",income.subtract(expense),"recent",records.stream().limit(5).collect(Collectors.toList()));
    }
    private boolean hasPermission(User user,String action){
        if(!user.isActive()) return false;
        return switch(user.getRole()){
            case "ADMIN"->true;
            case "ANALYST"->"READ".equals(action);
            case "VIEWER"->"READ".equals(action);
            default->false;
        };
    }
}
