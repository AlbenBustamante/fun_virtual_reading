package com.alnicode.funvirtualreading.persistence.entity;

import com.alnicode.funvirtualreading.enums.RoleType;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

/**
 * The role entity model.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    private Integer id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    /**
     * Create a role by the {@link RoleType} enum.
     *
     * @param roleType the enum to be used.
     */
    public Role(RoleType roleType) {
        this.id = roleType.getId();
        this.name = roleType.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
