package com.zorvyn.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="financial_records")
public class FinancialRecord {
    @Id @GeneratedValue private Long id;
    private BigDecimal amount;
    private String type, category, notes;
    private LocalDateTime date=LocalDateTime.now();
    private Long userId;
    public FinancialRecord(){}
    // Getters
    public Long getId(){return id;}
    public BigDecimal getAmount(){return amount;}
    public String getType(){return type;}
    public String getCategory(){return category;}
    // Setters
    public void setAmount(BigDecimal amount){this.amount=amount;}
    public void setType(String type){this.type=type;}
    public void setCategory(String category){this.category=category;}
    public void setNotes(String notes){this.notes=notes;}
    public void setUserId(Long userId){this.userId=userId;}
}
