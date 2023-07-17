package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import lab.space.vilki_palki_rest.service.AddressService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("addresses")
@AllArgsConstructor
@Tag(name = "Addresses", description = "Operations related to Addresses")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "Get address by id", description = "Enter your value")
    @GetMapping("get-address/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Address Id must be >=1");
        }
        return addressService.getAddressDto(id);
    }

    @Operation(summary = "Get all addresses", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @GetMapping("get-all-addresses/{page}")
    public ResponseEntity<?> getAllAddresses(@PathVariable int page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return addressService.getAllAddressByUser(page);
    }

    @Operation(summary = "Save address")
    @PostMapping("save-address")
    public ResponseEntity<?> saveAddress(@Valid @RequestBody AddressSaveRequest request,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        addressService.saveAddress(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update address by id", description = "Enter your value")
    @PutMapping("update-address")
    public ResponseEntity<?> updateAddress(@Valid @RequestBody AddressUpdateRequest request,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        return addressService.updateAddress(request);
    }

    @Operation(summary = "Delete address by id")
    @DeleteMapping("delete-address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Address Id must be >=1");
        }
        return addressService.deleteAddress(id);
    }
}
