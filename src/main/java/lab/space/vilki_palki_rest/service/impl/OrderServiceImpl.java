package lab.space.vilki_palki_rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.mapper.OrderMapper;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.repository.OrderRepository;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found by id " + id));
    }

    @Override
    public OrderResponse getOrderDto(Long id) {
        return OrderMapper.toSimplifiedDto(getOrder(id));
    }

    @Override
    public List<OrderResponse> getAllOrdersByUserId(Long id) {
        return orderRepository.findAllByUserIdOrderByCreateAt(id)
                .stream().map(OrderMapper::toSimplifiedDto).toList();
    }

    @Override
    public void saveOrder(OrderSaveRequest request) {
        orderRepository.save(
                new Order()
                        .setOrderCode(request.orderCode())
                        .setBirthday(request.date())
                        .setProducts(request.productsList())
                        .setDeliveryTime(Instant.now())
                        .setDeliveryStatus(Order.DeliveryStatus.ACCEPT)
                        .setAddress(request.address())
                        .setPrice(request.price())
                        .setUser(userService.getUserById(request.userId()))
        );
    }
}
