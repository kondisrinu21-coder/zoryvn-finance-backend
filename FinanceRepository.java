package com.zorvyn.repository;
import com.zorvyn.model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface FinanceRepository extends JpaRepository<FinancialRecord,Long>{
    List<FinancialRecord> findByUserId(Long userId);
    @Query("SELECT f FROM FinancialRecord f WHERE f.userId=?1 AND (?2 IS NULL OR f.category=?2) AND (?3 IS NULL OR f.type=?3)")
    List<FinancialRecord> findFiltered(Long userId,String category,String type);
}
