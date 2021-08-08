package com.gulyaich.bills.dao.role;

import com.gulyaich.bills.model.entity.Role;
import com.gulyaich.bills.model.entity.RoleType;

public interface RoleService {
    Role findByType(RoleType roleUser);
}
