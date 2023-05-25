package lab.space.vilki_palki_rest.model.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressUpdateRequest(
        Long id,
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String street,
        @NotBlank(message = "Must be specified")
        @Size(max = 10, message = "Must be no more than {max} symbols")
        String numberHouse,
        @Size(max = 10, message = "Must be no more than {max} symbols")
        String apartment,
        @Size(max = 20, message = "Must be no more than {max} symbols")
        String frontDoor,
        @Size(max = 20, message = "Must be no more than {max} symbols")
        String doorCode,
        Integer floor,
        @Size(max = 500, message = "Must be no more than {max} symbols")
        String notes,
        @NotNull(message = "Must be specified")
        Long userId
) {
}
