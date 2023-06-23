package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
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
import java.util.stream.Collectors;

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
                .stream().map(AddressMapper::toSimplifiedDto).collect(Collectors.toList());
    }

    @Override
    public void saveAddress(AddressSaveRequest request) {
        addressRepository.save(
                new Address()
                        .setApartment(request.getApartment())
                        .setDoorCode(request.getDoorCode())
                        .setNotes(request.getNotes())
                        .setUser(userService.getUserById(request.getUserId()))
                        .setFrontDoor(request.getFrontDoor())
                        .setNumberHouse(request.getNumberHouse())
                        .setStreet(request.getStreet())
                        .setFloor(request.getFloor())
        );
    }

    @Override
    public void updateAddress(AddressUpdateRequest request) {
        addressRepository.save(
                getAddress(request.getId())
                        .setApartment(request.getApartment())
                        .setDoorCode(request.getDoorCode())
                        .setNotes(request.getNotes())
                        .setUser(userService.getUserById(request.getUserId()))
                        .setFrontDoor(request.getFrontDoor())
                        .setNumberHouse(request.getNumberHouse())
                        .setStreet(request.getStreet())
                        .setFloor(request.getFloor())
        );

    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.delete(getAddress(id));
    }
}
