package com.inflatable.freelancer.controller;

import com.inflatable.freelancer.dto.OrderDTO;
import com.inflatable.freelancer.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Pedidos", description = "Operações CRUD para pedidos de aluguel")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Retorna todos os pedidos cadastrados")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Retorna um pedido específico pelo ID")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Buscar pedidos por cliente", description = "Retorna todos os pedidos de um cliente específico")
    public ResponseEntity<List<OrderDTO>> getOrdersByClient(@PathVariable Long clientId) {
        List<OrderDTO> orders = orderService.findByClientId(clientId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/freelancer/{freelancerId}")
    @Operation(summary = "Buscar pedidos por freelancer", description = "Retorna todos os pedidos de um freelancer específico")
    public ResponseEntity<List<OrderDTO>> getOrdersByFreelancer(@PathVariable Long freelancerId) {
        List<OrderDTO> orders = orderService.findByFreelancerId(freelancerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    @Operation(summary = "Criar pedido", description = "Cria um novo pedido de aluguel")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.create(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido", description = "Atualiza o status de um pedido existente")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        OrderDTO updatedOrder = orderService.updateStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pedido", description = "Remove um pedido do sistema")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}