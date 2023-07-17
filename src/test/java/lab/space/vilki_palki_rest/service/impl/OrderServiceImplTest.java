package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.mapper.OrderMapper;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.repository.OrderRepository;
import lab.space.vilki_palki_rest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testGetOrder_ExistingId_ReturnsOrder() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order result = orderService.getOrder(orderId);

        assertThat(result).isEqualTo(order);
    }

    @Test
    public void testGetOrder_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(orderRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrder(nonExistingId);
        });
    }

//    @Test
//    public void testGetOrderDto_ExistingId_ReturnsOrderResponse() {
//        Long orderId = 1L;
//        Order order = new Order();
//        order.setId(1L);
//        order.setDeliveryStatus(Order.DeliveryStatus.DONE);
//        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
//        OrderResponse expectedResponse = OrderMapper.toSimplifiedDto(order);
//
//        ResponseEntity<?> result = orderService.getOrderDto(orderId);
//
//        assertThat(result).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    public void testGetAllOrdersByUserId_ReturnsListOfOrderResponses() {
//        Long userId = 1L;
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED));
//        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED));
//        when(orderRepository.findAllByUserIdOrderByCreateAt(userId)).thenReturn(orders);
//        List<OrderResponse> expectedResponses = orders.stream()
//                .map(OrderMapper::toSimplifiedDto)
//                .collect(Collectors.toList());
//
//        ResponseEntity<?> ordersByUser  = orderService.getAllOrdersByUser();
//
//        assertThat(ordersByUser).isEqualTo(expectedResponses);
//    }
//
//    @Test
//    public void testSaveOrder_SavesOrder() {
//        OrderSaveRequest request = new OrderSaveRequest();
//        request.setOrderCode("ORDER123");
//        request.setDate(Instant.now());
//        request.setProductsList("gjgjgj");
//        request.setAddress("123 Main St");
//        request.setPrice(BigDecimal.valueOf(100L));
//
//        when(userService.getUserById(1L)).thenReturn(new User());
//
//        orderService.saveOrder(request);
//
//        verify(orderRepository, times(1)).save(any(Order.class));
//    }
}
