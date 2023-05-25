package lab.space.vilki_palki_rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.mapper.AddressMapper;
import lab.space.vilki_palki_rest.model.address.AddressResponse;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import lab.space.vilki_palki_rest.repository.AddressRepository;
import lab.space.vilki_palki_rest.service.AddressService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    public Address getAddress(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found by id " + id));
    }

    @Override
    public AddressResponse getAddressDto(Long id) {
        return AddressMapper.toSimplifiedDto(getAddress(id));
    }

    @Override
    public List<AddressResponse> getAllAddressByUserId(Long id) {
        return addressRepository.findAllByUserIdOrderByCreateAt(id)
                .stream().map(AddressMapper::toSimplifiedDto).toList();
    }

    @Override
    public void saveAddress(AddressSaveRequest request) {
        addressRepository.save(
                new Address()
                        .setApartment(request.apartment())
                        .setDoorCode(request.doorCode())
                        .setNotes(request.notes())
                        .setUser(userService.getUserById(request.userId()))
                        .setFrontDoor(request.frontDoor())
                        .setNumberHouse(request.numberHouse())
                        .setStreet(request.street())
                        .setFloor(request.floor())
        );
    }

    @Override
    public void updateAddress(AddressUpdateRequest request) {
        addressRepository.save(
                getAddress(request.id())
                        .setApartment(request.apartment())
                        .setDoorCode(request.doorCode())
                        .setNotes(request.notes())
                        .setUser(userService.getUserById(request.userId()))
                        .setFrontDoor(request.frontDoor())
                        .setNumberHouse(request.numberHouse())
                        .setStreet(request.street())
                        .setFloor(request.floor())
        );

    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.delete(getAddress(id));
    }
}
