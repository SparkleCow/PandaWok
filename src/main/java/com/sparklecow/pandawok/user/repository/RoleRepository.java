package com.sparklecow.pandawok.user.repository;

import com.sparklecow.pandawok.user.entity.Role;
import com.sparklecow.pandawok.user.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
