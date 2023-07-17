package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.mapper.AddressMapper;
import lab.space.vilki_palki_rest.model.address.AddressResponse;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import lab.space.vilki_palki_rest.repository.AddressRepository;
import lab.space.vilki_palki_rest.service.UserService;

public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAddress_ExistingId_ReturnsAddress() {
        Long addressId = 1L;
        Address address = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Address result = addressService.getAddress(addressId);

        assertThat(result).isEqualTo(address);
    }

    @Test
    public void testGetAddress_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(addressRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            addressService.getAddress(nonExistingId);
        });
    }

    @Test
    public void testGetAddressDto_ExistingId_ReturnsAddressResponse() {
        // Arrange
        Long addressId = 1L;
        Address address = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        AddressResponse expectedResponse = AddressMapper.toSimplifiedDto(address);

        AddressResponse result = addressService.getAddressDto(addressId);

        assertThat(result).isEqualTo(expectedResponse);
    }

//    @Test
//    public void testGetAllAddressByUserId_ReturnsListOfAddressResponses() {
//        Long userId = 1L;
//        List<Address> addresses = new ArrayList<>();
//        addresses.add(new Address());
//        addresses.add(new Address());
//        when(addressRepository.findAllByUserIdOrderByCreateAt(userId)).thenReturn(addresses);
//        List<AddressResponse> expectedResponses = addresses.stream()
//                .map(AddressMapper::toSimplifiedDto)
//                .collect(Collectors.toList());
//
//        List<AddressResponse> result = addressService.getAllAddressByUser();
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }

//    @Test
//    public void testSaveAddress_SavesAddress() {
//        AddressSaveRequest request = new AddressSaveRequest();
//        request.setApartment("123");
//        request.setDoorCode("456");
//
//        addressService.saveAddress(request);
//
//        verify(addressRepository, times(1)).save(any(Address.class));
//    }
//
//    @Test
//    public void testUpdateAddress_UpdatesAddress() {
//        Long addressId = 1L;
//        AddressUpdateRequest request = new AddressUpdateRequest();
//        request.setId(addressId);
//        request.setApartment("123");
//        request.setDoorCode("456");
//        Address existingAddress = new Address();
//        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
//
//        addressService.updateAddress(request);
//
//        assertThat(existingAddress.getApartment()).isEqualTo(request.getApartment());
//        assertThat(existingAddress.getDoorCode()).isEqualTo(request.getDoorCode());
//        verify(addressRepository, times(1)).save(existingAddress);
//    }
//
//    @Test
//    public void testUpdateAddress_NonExistingId_ThrowsEntityNotFoundException() {
//        Long nonExistingId = 2L;
//        AddressUpdateRequest request = new AddressUpdateRequest();
//        request.setId(nonExistingId);
//        request.setApartment("123");
//        request.setDoorCode("456");
//        when(addressRepository.findById(nonExistingId)).thenReturn(Optional.empty());
//
//        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
//            addressService.updateAddress(request);
//        });
//        verify(addressRepository, never()).save(any(Address.class));
//    }
//
//    @Test
//    public void testDeleteAddress_DeletesAddress() {
//        Long addressId = 1L;
//        Address existingAddress = new Address();
//        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
//
//        addressService.deleteAddress(addressId);
//
//        verify(addressRepository, times(1)).delete(existingAddress);
//    }
//
//    @Test
//    public void testDeleteAddress_NonExistingId_ThrowsEntityNotFoundException() {
//        Long nonExistingId = 2L;
//        when(addressRepository.findById(nonExistingId)).thenReturn(Optional.empty());
//
//        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
//            addressService.deleteAddress(nonExistingId);
//        });
//        verify(addressRepository, never()).delete(any(Address.class));
//    }

}
