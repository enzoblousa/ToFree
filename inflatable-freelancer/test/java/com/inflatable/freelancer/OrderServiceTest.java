package com.inflatable.freelancer;

import com.inflatable.freelancer.dto.OrderDTO;
import com.inflatable.freelancer.model.Order;
import com.inflatable.freelancer.model.OrderStatus;
import com.inflatable.freelancer.repository.OrderRepository;
import com.inflatable.freelancer.service.UserService;
import com.inflatable.freelancer.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void shouldFindAllOrders() {
        Order order = new Order();
        order.setId(1L);
        order.setClientId(1L);
        order.setTotalPrice(new BigDecimal("300.00"));

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));

        List<OrderDTO> orders = orderService.findAll();

        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        assertEquals(1L, orders.get(0).getClientId());
    }

    @Test
    void shouldFindOrdersByClientId() {
        Order order = new Order();
        order.setId(1L);
        order.setClientId(1L);

        when(orderRepository.findByClientId(1L)).thenReturn(Arrays.asList(order));

        List<OrderDTO> orders = orderService.findByClientId(1L);

        assertFalse(orders.isEmpty());
        assertEquals(1L, orders.get(0).getClientId());
    }

    @Test
    void shouldUpdateOrderStatus() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDTO updated = orderService.updateStatus(1L, "confirmed");

        assertNotNull(updated);
        assertEquals(OrderStatus.CONFIRMED, updated.getStatus());
    }
}