package com.inflatable.freelancer.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inflatables")
public class Inflatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay;
    
    private String size;
    
    private Boolean available = true;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    // Construtores
    public Inflatable() {}
    
    public Inflatable(String name, String description, BigDecimal pricePerDay, String size, Long createdBy) {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.size = size;
        this.createdBy = createdBy;
        this.available = true;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
    
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
}