package org.application.openschoolhomeworkaop;

import org.application.openschoolhomeworkaop.exceptions.ElementNotFoundException;
import org.application.openschoolhomeworkaop.models.Order;
import org.application.openschoolhomeworkaop.models.OrderStatus;
import org.application.openschoolhomeworkaop.models.User;
import org.application.openschoolhomeworkaop.repositories.OrderRepository;
import org.application.openschoolhomeworkaop.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class OrderServiceTest {
    private final Long ID = 1L;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;
    private Order order;

    @BeforeEach
    public void setUp(){
        order = new Order();
        order.setId(1L);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setUser(new User(1L));
    }

    @Test
    public void givenOrderId_whenGetOrderById_thenReturnOrder(){
        given(orderRepository.findById(ID)).willReturn(Optional.of(order));

        Order actualOrder = orderService.getOrderById(ID);

        assertEquals(order, actualOrder);
    }

    @Test
    public void givenNotExistOrderId_whenGetOrderById_thenThrowException(){
        given(orderRepository.findById(ID)).willReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, ()-> orderService.getOrderById(ID));

        verify(orderRepository).findById(anyLong());
    }

    @Test
    public void givenExistingOrder_whenUpdateOrder_thenOrderIdEqualsUpdatedOrder(){
        given(orderRepository.save(order)).willReturn(order);

        orderService.updateOrderById(999L, order);

        assertEquals(999L, order.getId());
        verify(orderRepository).save(any(Order.class));
    }

}
