package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
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
import org.springframework.stereotype.Service;

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
    public List<ShoppingCartResponse> getAllShoppingCartByUserId(Long id) {
        return shoppingCartRepository.findAllByUserId(id)
                .stream().map(shoppingCartMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.delete(getShoppingCart(id));
    }

    @Override
    public void saveShoppingCart(ShoppingCartSaveRequest request) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCount(request.getCount());
        shoppingCart.setUser(userService.getUserById(request.getUserId()));
        shoppingCart.setProduct(productService.getProduct(request.getProductId()));

        shoppingCartRepository.save(shoppingCart);
    }
}
