package lab.space.vilki_palki_rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lab.space.vilki_palki_rest.entity.common.MappedEntity;
import java.math.BigDecimal;

@Entity
@Table(name = "promotions")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Promotion extends MappedEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Long percent;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Column(length = 150, nullable = false)
    private String image;
    @OneToOne
    private Product product;
}
