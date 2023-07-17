package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    Order getOrder(Long id);
    ResponseEntity<?> getOrderDto(Long id);
    Page<OrderResponse> getAllOrdersByUser(int page);
    void saveOrder(OrderSaveRequest request);
}
