package lab.space.vilki_palki_rest.controller;

import jakarta.validation.Valid;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("get-order/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderDto(id));
    }

    @GetMapping("get-all-orders-by-user-id/{id}")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(id));
    }

    @PostMapping("save-order")
    public ResponseEntity<?> getAllOrders(@Valid @RequestBody OrderSaveRequest request,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        orderService.saveOrder(request);
        return ResponseEntity.ok().build();
    }
}
