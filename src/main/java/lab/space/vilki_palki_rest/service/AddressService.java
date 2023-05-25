package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.model.address.AddressResponse;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;

import java.util.List;

public interface AddressService {
    Address getAddress(Long id);
    AddressResponse getAddressDto(Long id);
    List<AddressResponse> getAllAddressByUserId(Long id);
    void saveAddress(AddressSaveRequest request);
    void updateAddress(AddressUpdateRequest request);
    void deleteAddress(Long id);
}
