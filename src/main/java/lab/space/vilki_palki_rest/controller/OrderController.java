package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.mapper.AddressMapper;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.service.UserService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
@Tag(name = "Orders", description = "Operations related to Orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Operation(summary = "Get order by id", description = "Enter your value")
    @GetMapping("get-order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return orderService.getOrderDto(id);
    }

    @Operation(summary = "Get all orders by user id", description = "Enter your value")
    @GetMapping("get-all-orders")
    public ResponseEntity<?> getAllOrders() {
        if (!userService.getCurrentUser().getAddresses().isEmpty()) {
            return ResponseEntity.ok(orderService.getAllOrdersByUser());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address not found");
        }
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
