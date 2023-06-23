package lab.space.vilki_palki_rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lab.space.vilki_palki_rest.entity.common.MappedEntity;

@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends MappedEntity {
    @Column(length = 50, nullable = false)
    private String firstname;
    @Column(length = 50, nullable = false)
    private String lastname;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    private int securityLevel;
}
