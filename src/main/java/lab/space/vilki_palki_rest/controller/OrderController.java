package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.order.OrderSaveRequest;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
@Tag(name = "Orders", description = "Operations related to Orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get order by id", description = "Enter your value")
    @GetMapping("get-order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Order Id must be >=1");
        }
        return orderService.getOrderDto(id);
    }

    @Operation(summary = "Get all orders", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @GetMapping("get-all-orders/{page}")
    public ResponseEntity<?> getAllOrders(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(orderService.getAllOrdersByUser(page));
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
