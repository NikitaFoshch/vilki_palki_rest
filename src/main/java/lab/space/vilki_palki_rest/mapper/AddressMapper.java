package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Address;
import lab.space.vilki_palki_rest.model.address.AddressResponse;


public interface AddressMapper {
    static AddressResponse toSimplifiedDto(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .numberHouse(address.getNumberHouse())
                .apartment(address.getApartment())
                .frontDoor(address.getFrontDoor())
                .doorCode(address.getDoorCode())
                .floor(address.getFloor())
                .notes(address.getNotes())
                .build();
    }
}
