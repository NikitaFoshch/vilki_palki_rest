package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import lab.space.vilki_palki_rest.mapper.ShoppingCartMapper;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;
import lab.space.vilki_palki_rest.repository.ShoppingCartRepository;
import lab.space.vilki_palki_rest.service.ProductService;
import lab.space.vilki_palki_rest.service.ShoppingCartService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final ProductService productService;
    private final ShoppingCartSpecification specification;
    private final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public ShoppingCart getShoppingCart(Long id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found by id " + id));
    }

    @Override
    public Page<ShoppingCartResponse> getAllShoppingCartByUser(Integer page) {
        return shoppingCartRepository.findAll(specification.getShoppingCartsByUser(userService.getCurrentUser().getId()),
                        PageRequest.of(page, DEFAULT_PAGE_SIZE))
                .map(shoppingCartMapper::toDto);
    }

    @Override
    public ResponseEntity<?> deleteShoppingCart(Long id) {
        if (userService.getCurrentUserWithoutDto().getShoppingCarts()
                .stream()
                .anyMatch(shoppingCart -> shoppingCart.getProduct().getId().equals(id))) {
            List<ShoppingCart> shoppingCarts = userService.getCurrentUserWithoutDto().getShoppingCarts();

            shoppingCarts.removeIf(shoppingCart -> shoppingCart.getProduct().getId().equals(id));

            shoppingCartRepository.deleteAll(shoppingCarts);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product in Shopping Cart not found with id " + id);
        }

    }

    @Override
    public ResponseEntity<?> saveShoppingCart(ShoppingCartSaveRequest request) {
        try {
            ShoppingCart cart = new ShoppingCart();
            if (userService.getCurrentUserWithoutDto().getShoppingCarts()
                    .stream()
                    .noneMatch(shoppingCart -> shoppingCart.getProduct().getId().equals(request.getProductId()))) {
                cart.setCount(request.getCount());
                cart.setUser(userService.getUserById(userService.getCurrentUser().getId()));
                cart.setProduct(productService.getProduct(request.getProductId()));
            }else {
                Optional<ShoppingCart> shoppingCartOptional = userService.getCurrentUserWithoutDto().getShoppingCarts()
                        .stream()
                        .filter(shoppingCart -> shoppingCart.getProduct().getId().equals(request.getProductId()))
                        .findFirst();
                if (shoppingCartOptional.isPresent()) {
                    cart = getShoppingCart(shoppingCartOptional.get().getId());
                    cart.setCount(cart.getCount()+ request.getCount());
                }
            }
            shoppingCartRepository.save(cart);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id " + request.getProductId());
        }
    }
}
