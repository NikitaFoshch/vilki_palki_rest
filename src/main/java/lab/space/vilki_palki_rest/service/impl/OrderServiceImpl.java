package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.mapper.OrderMapper;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.repository.OrderRepository;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getOrderDto(Long id) {
        if (!userService.getCurrentUser().getOrders().isEmpty()) {
            return ResponseEntity.ok(OrderMapper.toSimplifiedDto(getOrder(id)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Orders not found");
        }
    }

    @Override
    public List<OrderResponse> getAllOrdersByUser() {
            return orderRepository.findAllByUserIdOrderByCreateAt(userService.getCurrentUser().getId())
                    .stream().map(OrderMapper::toSimplifiedDto).collect(Collectors.toList());
    }

    @Override
    public void saveOrder(OrderSaveRequest request) {
        orderRepository.save(
                new Order()
                        .setOrderCode(request.getOrderCode())
                        .setBirthday(null)
                        .setProducts(request.getProductsList())
                        .setDeliveryTime(Instant.now())
                        .setDeliveryStatus(Order.DeliveryStatus.ACCEPT)
                        .setAddress(request.getAddress())
                        .setPrice(request.getPrice())
                        .setUser(userService.getUserById(userService.getCurrentUser().getId()))
        );
    }
}
