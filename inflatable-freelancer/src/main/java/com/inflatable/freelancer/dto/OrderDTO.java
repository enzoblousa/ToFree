package com.inflatable.freelancer.dto;

import com.inflatable.freelancer.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private Long clientId;
    private Long inflatableId;
    private Long freelancerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer days;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    // Construtores
    public OrderDTO() {}
    
    public OrderDTO(Long id, Long clientId, Long inflatableId, Long freelancerId, 
                   LocalDate startDate, LocalDate endDate, Integer days, 
                   BigDecimal totalPrice, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.clientId = clientId;
        this.inflatableId = inflatableId;
        this.freelancerId = freelancerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    
    public Long getInflatableId() { return inflatableId; }
    public void setInflatableId(Long inflatableId) { this.inflatableId = inflatableId; }
    
    public Long getFreelancerId() { return freelancerId; }
    public void setFreelancerId(Long freelancerId) { this.freelancerId = freelancerId; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public Integer getDays() { return days; }
    public void setDays(Integer days) { this.days = days; }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}