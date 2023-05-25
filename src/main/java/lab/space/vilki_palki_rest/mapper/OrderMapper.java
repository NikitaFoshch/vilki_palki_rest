package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.model.order.OrderResponse;

public interface OrderMapper {
    static OrderResponse toSimplifiedDto(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .productsList(order.getProducts())
                .date(order.getCreateAt())
                .deliveryStatus(order.getDeliveryStatus().getValue())
                .address(order.getAddress())
                .price(order.getPrice())
                .build();
    }
}
