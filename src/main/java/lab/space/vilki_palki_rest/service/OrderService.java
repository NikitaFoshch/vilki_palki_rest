package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    Order getOrder(Long id);
    ResponseEntity<?> getOrderDto(Long id);
    ResponseEntity<?> getAllOrdersByUser();
    void saveOrder(OrderSaveRequest request);
}
