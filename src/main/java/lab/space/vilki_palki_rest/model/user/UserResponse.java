package lab.space.vilki_palki_rest.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki_rest.model.address.AddressResponse;
import lab.space.vilki_palki_rest.model.order.OrderResponse;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "UTC")
    private Instant birthday;
    private String facebookId;
    private String phoneNumber;
    private Integer sumOrders;
    private String cardNumber;
    private List<OrderResponse> orders;
    private List<AddressResponse> addresses;
}
