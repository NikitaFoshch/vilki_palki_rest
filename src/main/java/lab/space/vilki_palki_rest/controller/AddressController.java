package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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

import java.util.List;

@RestController
@RequestMapping("addresses")
@AllArgsConstructor
@Tag(name = "Addresses", description = "Operations related to Addresses")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "Get address by id" , description = "Enter your value")
    @GetMapping("get-address/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressDto(id));
    }
    @Operation(summary = "Get all addresses by user id", description = "Enter your value")
    @GetMapping("get-all-addresses-by-user-id/{id}")
    public ResponseEntity<List<AddressResponse>> getAllAddresses(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAllAddressByUserId(id));
    }

    @Operation(summary = "Save address")
    @PostMapping("save-address")
    public ResponseEntity<?> saveAddress(@Valid @RequestBody AddressSaveRequest request,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        try {
            addressService.saveAddress(request);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + request.getUserId());
        }
    }

    @Operation(summary = "Update address", description = "Enter your value")
    @PutMapping("update-address")
    public ResponseEntity<?> updateAddress(@Valid @RequestBody AddressUpdateRequest request,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        try {
            addressService.updateAddress(request);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address or User not found");
        }
    }

    @Operation(summary = "Delete address by id")
    @DeleteMapping("delete-address/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
