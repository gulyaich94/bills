package com.gulyaich.bills.dao.user;

import com.gulyaich.bills.model.entity.Role;
import com.gulyaich.bills.model.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByType(RoleType roleType);
}
