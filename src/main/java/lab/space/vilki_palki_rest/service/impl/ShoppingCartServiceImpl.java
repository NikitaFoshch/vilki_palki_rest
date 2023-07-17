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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public ShoppingCart getShoppingCart(Long id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found by id " + id));
    }

    @Override
    public List<ShoppingCartResponse> getAllShoppingCartByUserId() {
        return shoppingCartRepository.findAllByUserId(userService.getCurrentUser().getId())
                .stream().map(shoppingCartMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> deleteShoppingCart(Long id) {
        if (userService.getCurrentUser().getId().equals(getShoppingCart(id).getUser().getId())) {
            shoppingCartRepository.delete(getShoppingCart(id));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Shopping Cart not found with id " + id);
        }

    }

    @Override
    public ResponseEntity<?> saveShoppingCart(ShoppingCartSaveRequest request) {
        try{
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCount(request.getCount());
        shoppingCart.setUser(userService.getUserById(userService.getCurrentUser().getId()));
        shoppingCart.setProduct(productService.getProduct(request.getProductId()));

        shoppingCartRepository.save(shoppingCart);
        return ResponseEntity.ok().build();
        }catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id " + request.getProductId());
        }
    }
}
