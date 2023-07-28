package lab.space.vilki_palki_rest.model.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class AddressSaveRequest {
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
    @Max(value = 100, message = "Must be no more than {value}")
    @Min(value = -10, message = "Must be no less than {value}")
    @Schema(name = "floor", example = "17")
    private Integer floor;
    @Size(max = 500, message = "Must be no more than {max} symbols")
    @Schema(name = "notes", example = "Bober zaberet")
    private String notes;
}
