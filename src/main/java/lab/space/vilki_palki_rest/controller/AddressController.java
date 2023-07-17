package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.address.AddressResponse;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import lab.space.vilki_palki_rest.service.AddressService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        return addressService.getAddressDto(id);
    }

    @Operation(summary = "Get all addresses")
    @GetMapping("get-all-addresses")
    public ResponseEntity<?> getAllAddresses() {
        return addressService.getAllAddressByUser();
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
        return addressService.deleteAddress(id);
    }
}
