package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
@Tag(name = "Orders", description = "Operations related to Orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get order by id", description = "Enter your value")
    @GetMapping("get-order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return orderService.getOrderDto(id);
    }

    @Operation(summary = "Get all orders by user id", description = "Enter your value")
    @GetMapping("get-all-orders")
    public ResponseEntity<?> getAllOrders() {
        return orderService.getAllOrdersByUser();
    }

    @Operation(summary = "Save order")
    @PostMapping("save-order")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderSaveRequest request,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        orderService.saveOrder(request);
        return ResponseEntity.ok().build();
    }
}
