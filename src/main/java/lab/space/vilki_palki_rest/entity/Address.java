package lab.space.vilki_palki_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lab.space.vilki_palki_rest.entity.common.MappedEntity;
import lombok.experimental.Accessors;

@Entity
@Table(name = "addresses")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Address extends MappedEntity {

    @Column(length = 100, nullable = false)
    private String street;
    @Column(length = 10, nullable = false)
    private String numberHouse;
    @Column(length = 10)
    private String apartment;
    @Column(length = 20)
    private String frontDoor;
    @Column(length = 20)
    private String doorCode;
    private Integer floor;
    @Column(length = 500)
    private String notes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
