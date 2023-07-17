package lab.space.vilki_palki_rest.model.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressUpdateRequest{
        @Min(value = 1, message = "Must be positive")
        private Long id;
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        @Schema(name = "street", example = "Bobra-Velikogo")
        private String street;
        @NotBlank(message = "Must be specified")
        @Size(max = 10, message = "Must be no more than {max} symbols")
        @Schema(name = "numberHouse", example = "97")
        private String numberHouse;
        @Size(max = 10, message = "Must be no more than {max} symbols")
        @Schema(name = "apartment", example = "77")
        private String apartment;
        @Size(max = 20, message = "Must be no more than {max} symbols")
        @Schema(name = "frontDoor", example = "6")
        private String frontDoor;
        @Size(max = 20, message = "Must be no more than {max} symbols")
        @Schema(name = "doorCode", example = "1197")
        private String doorCode;
        @Schema(name = "floor", example = "17")
        private Integer floor;
        @Size(max = 500, message = "Must be no more than {max} symbols")
        @Schema(name = "notes", example = "Bober zaberet")
        private String notes;
}
