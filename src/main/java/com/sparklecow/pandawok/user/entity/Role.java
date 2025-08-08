package com.sparklecow.pandawok.user.entity;

import com.sparklecow.pandawok.user.model.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "roles")
public class Role implements GrantedAuthority {

    private RoleEnum role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
