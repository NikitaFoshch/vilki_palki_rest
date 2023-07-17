package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.mapper.AddressMapper;
import lab.space.vilki_palki_rest.model.address.AddressSaveRequest;
import lab.space.vilki_palki_rest.model.address.AddressUpdateRequest;
import lab.space.vilki_palki_rest.repository.AddressRepository;
import lab.space.vilki_palki_rest.service.AddressService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<?> getAddressDto(Long id) {
        if (!userService.getCurrentUser().getOrders().isEmpty()) {
            return ResponseEntity.ok(AddressMapper.toSimplifiedDto(getAddress(id)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address not found");
        }
    }

    @Override
    public ResponseEntity<?> getAllAddressByUser() {
        if (!userService.getCurrentUser().getAddresses().isEmpty()) {
            return ResponseEntity.ok(addressRepository.findAllByUserIdOrderByCreateAt(userService.getCurrentUser().getId())
                    .stream().map(AddressMapper::toSimplifiedDto).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address not found");
        }
    }

    @Override
    public void saveAddress(AddressSaveRequest request) {
        addressRepository.save(
                new Address()
                        .setApartment(request.getApartment())
                        .setDoorCode(request.getDoorCode())
                        .setNotes(request.getNotes())
                        .setUser(userService.getUserById(userService.getCurrentUser().getId()))
                        .setFrontDoor(request.getFrontDoor())
                        .setNumberHouse(request.getNumberHouse())
                        .setStreet(request.getStreet())
                        .setFloor(request.getFloor())
        );
    }

    @Override
    public ResponseEntity<?> updateAddress(AddressUpdateRequest request) {
        if (!userService.getCurrentUser().getAddresses().isEmpty()
                && userService.getCurrentUser().getAddresses()
                .stream()
                .anyMatch(addressResponse -> addressResponse.getId().equals(request.getId()))) {
            return ResponseEntity.ok(
                    addressRepository.save(
                            getAddress(request.getId())
                                    .setApartment(request.getApartment())
                                    .setDoorCode(request.getDoorCode())
                                    .setNotes(request.getNotes())
                                    .setUser(userService.getUserById(userService.getCurrentUser().getId()))
                                    .setFrontDoor(request.getFrontDoor())
                                    .setNumberHouse(request.getNumberHouse())
                                    .setStreet(request.getStreet())
                                    .setFloor(request.getFloor())
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address not found");
        }


    }

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        if (userService.getCurrentUser().getId().equals(getAddress(id).getUser().getId())) {
            addressRepository.delete(getAddress(id));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address not found with id " + id);
        }
    }
}
