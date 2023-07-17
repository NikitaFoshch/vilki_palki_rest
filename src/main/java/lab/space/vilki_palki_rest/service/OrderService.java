package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    Order getOrder(Long id);
    ResponseEntity<?> getOrderDto(Long id);
    List<OrderResponse> getAllOrdersByUser();
    void saveOrder(OrderSaveRequest request);
}
