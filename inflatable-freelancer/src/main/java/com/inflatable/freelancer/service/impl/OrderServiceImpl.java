package com.inflatable.freelancer.service.impl;

import com.inflatable.freelancer.dto.OrderDTO;
import com.inflatable.freelancer.model.Order;
import com.inflatable.freelancer.model.OrderStatus;
import com.inflatable.freelancer.repository.OrderRepository;
import com.inflatable.freelancer.service.OrderService;
import com.inflatable.freelancer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return convertToDTO(order);
    }

    @Override
    public List<OrderDTO> findByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByFreelancerId(Long freelancerId) {
        return orderRepository.findByFreelancerId(freelancerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        order.setClientId(userService.getCurrentUser().getId());
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    @Override
    public OrderDTO updateStatus(Long id, String status) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            existingOrder.setStatus(newStatus);
            
            Order updatedOrder = orderRepository.save(existingOrder);
            return convertToDTO(updatedOrder);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido: " + status);
        }
    }

    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getClientId(),
            order.getInflatableId(),
            order.getFreelancerId(),
            order.getStartDate(),
            order.getEndDate(),
            order.getDays(),
            order.getTotalPrice(),
            order.getStatus(),
            order.getCreatedAt()
        );
    }

    private Order convertToEntity(OrderDTO dto) {
        return new Order(
            dto.getClientId(),
            dto.getInflatableId(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getDays(),
            dto.getTotalPrice()
        );
    }
}