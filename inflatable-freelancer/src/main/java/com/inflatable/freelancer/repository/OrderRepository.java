package com.inflatable.freelancer.repository;

import com.inflatable.freelancer.model.Order;
import com.inflatable.freelancer.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(Long clientId);
    List<Order> findByFreelancerId(Long freelancerId);
    List<Order> findByStatus(OrderStatus status);
}