package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.model.address.AddressResponse;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    Address getAddress(Long id);
    AddressResponse getAddressDto(Long id);
    ResponseEntity<?> getAllAddressByUser();
    void saveAddress(AddressSaveRequest request);
    ResponseEntity<?> updateAddress(AddressUpdateRequest request);
    ResponseEntity<?> deleteAddress(Long id);
}
