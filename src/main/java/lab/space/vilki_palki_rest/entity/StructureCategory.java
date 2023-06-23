package lab.space.vilki_palki_rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lab.space.vilki_palki_rest.entity.common.MappedEntity;

@Entity
@Table(name = "structure_categories")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class StructureCategory extends MappedEntity {
    @Column(length = 20, nullable = false, unique = true)
    private String name;
}
