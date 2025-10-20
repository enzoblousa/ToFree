package com.inflatable.freelancer.service;

import com.inflatable.freelancer.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAll();
    OrderDTO findById(Long id);
    List<OrderDTO> findByClientId(Long clientId);
    List<OrderDTO> findByFreelancerId(Long freelancerId);
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO updateStatus(Long id, String status);
    void delete(Long id);
}