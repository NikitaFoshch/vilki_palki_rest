package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Order;
import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .birthday(user.getBirthday())
                .facebookId(user.getFacebookId())
                .phoneNumber(user.getPhone())
                .cardNumber(user.getCardNumber())
                .addresses(user.getAddresses().stream().map(AddressMapper::toSimplifiedDto).collect(Collectors.toList()))
                .orders(user.getOrders().stream().map(OrderMapper::toSimplifiedDto).collect(Collectors.toList()))
                .sumOrders(user.getOrders().stream().map(Order::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                        .intValue())
                .build();
    }
}
