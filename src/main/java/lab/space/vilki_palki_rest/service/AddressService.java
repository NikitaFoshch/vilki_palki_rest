package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    Address getAddress(Long id);
    ResponseEntity<?> getAddressDto(Long id);
    ResponseEntity<?> getAllAddressByUser(Integer page);
    void saveAddress(AddressSaveRequest request);
    ResponseEntity<?> updateAddress(AddressUpdateRequest request);
    ResponseEntity<?> deleteAddress(Long id);
}
