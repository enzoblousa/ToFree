package com.inflatable.freelancer.dto;

import java.math.BigDecimal;

public class InflatableDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal pricePerDay;
    private String size;
    private Boolean available;
    private Long createdBy;

    // Construtores
    public InflatableDTO() {}
    
    public InflatableDTO(Long id, String name, String description, BigDecimal pricePerDay, 
                        String size, Boolean available, Long createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.size = size;
        this.available = available;
        this.createdBy = createdBy;
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