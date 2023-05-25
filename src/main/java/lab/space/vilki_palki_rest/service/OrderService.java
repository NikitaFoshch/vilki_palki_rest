package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;

import java.util.List;

public interface OrderService {
    Order getOrder(Long id);
    OrderResponse getOrderDto(Long id);
    List<OrderResponse> getAllOrdersByUserId(Long id);
    void saveOrder(OrderSaveRequest request);
}
