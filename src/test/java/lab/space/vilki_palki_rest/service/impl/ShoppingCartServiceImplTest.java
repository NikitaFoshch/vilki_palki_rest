package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab.space.vilki_palki_rest.entity.Product;
import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import lab.space.vilki_palki_rest.mapper.ShoppingCartMapper;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;
import lab.space.vilki_palki_rest.repository.ShoppingCartRepository;
import lab.space.vilki_palki_rest.service.ProductService;
import lab.space.vilki_palki_rest.service.UserService;
import lab.space.vilki_palki_rest.service.ShoppingCartService;

public class ShoppingCartServiceImplTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetShoppingCart_ExistingId_ReturnsShoppingCart() {
        Long shoppingCartId = 1L;
        ShoppingCart shoppingCart = new ShoppingCart();
        when(shoppingCartRepository.findById(shoppingCartId)).thenReturn(Optional.of(shoppingCart));

        ShoppingCart result = shoppingCartService.getShoppingCart(shoppingCartId);

        assertThat(result).isEqualTo(shoppingCart);
    }

    @Test
    public void testGetShoppingCart_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(shoppingCartRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            shoppingCartService.getShoppingCart(nonExistingId);
        });
    }

//    @Test
//    public void testGetAllShoppingCartByUserId_ReturnsListOfShoppingCartResponses() {
//        Long userId = 1L;
//        List<ShoppingCart> shoppingCarts = new ArrayList<>();
//        shoppingCarts.add(new ShoppingCart());
//        shoppingCarts.add(new ShoppingCart());
//
//        when(shoppingCartRepository.findAllByUserId(userId)).thenReturn(shoppingCarts);
//
//        List<ShoppingCartResponse> expectedResponses = shoppingCarts.stream()
//                .map(shoppingCartMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<ShoppingCartResponse> result = shoppingCartService.getAllShoppingCartByUserId();
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }
//
//    @Test
//    public void testDeleteShoppingCart_ExistingId_DeletesShoppingCart() {
//        Long shoppingCartId = 1L;
//        ShoppingCart shoppingCart = new ShoppingCart();
//        when(shoppingCartRepository.findById(shoppingCartId)).thenReturn(Optional.of(shoppingCart));
//
//        shoppingCartService.deleteShoppingCart(shoppingCartId);
//
//        verify(shoppingCartRepository, times(1)).delete(shoppingCart);
//    }
//
//    @Test
//    public void testDeleteShoppingCart_NonExistingId_ThrowsEntityNotFoundException() {
//        Long nonExistingId = 2L;
//        when(shoppingCartRepository.findById(nonExistingId)).thenReturn(Optional.empty());
//
//        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
//            shoppingCartService.deleteShoppingCart(nonExistingId);
//        });
//    }
//
//    @Test
//    public void testSaveShoppingCart_SavesShoppingCart() {
//        ShoppingCartSaveRequest request = new ShoppingCartSaveRequest();
//        request.setCount(2);
//        request.setProductId(1L);
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        when(userService.getUserById(1L)).thenReturn(new User());
//        when(productService.getProduct(request.getProductId())).thenReturn(new Product());
//
//        shoppingCartService.saveShoppingCart(request);
//
//        verify(shoppingCartRepository, times(0)).save(shoppingCart);
//    }
}
