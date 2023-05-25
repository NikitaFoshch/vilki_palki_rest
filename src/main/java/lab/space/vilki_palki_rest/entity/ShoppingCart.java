package lab.space.vilki_palki_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lab.space.vilki_palki_rest.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "shopping_carts")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingCart extends MappedEntity {
    private int count;
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne
    private Product product;
}
