package lab.space.vilki_palki_rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lab.space.vilki_palki_rest.entity.common.MappedEntity;

@Entity
@Table(name = "banners")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Banner extends MappedEntity {
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String image;
}
