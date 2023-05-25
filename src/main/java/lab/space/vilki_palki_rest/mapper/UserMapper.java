package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
                .addresses(null)
                .orders(null)
                .sumOrders(null)
                .build();
    }
}
