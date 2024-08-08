package org.application.openschoolhomeworkaop.services;

import jakarta.transaction.Transactional;
import org.application.openschoolhomeworkaop.exceptions.ElementNotFoundException;
import org.application.openschoolhomeworkaop.models.Order;
import org.application.openschoolhomeworkaop.models.User;
import org.application.openschoolhomeworkaop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new ElementNotFoundException("Заказа нет"));
    }

    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateOrderById(Long id, Order updatedOrder){
        updatedOrder.setId(id);
        orderRepository.save(updatedOrder);
    }
}
