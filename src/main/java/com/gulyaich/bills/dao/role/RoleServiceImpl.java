package com.gulyaich.bills.dao.role;

import com.gulyaich.bills.dao.user.RoleRepository;
import com.gulyaich.bills.model.entity.Role;
import com.gulyaich.bills.model.entity.RoleType;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByType(RoleType roleUser) {
        return roleRepository.findByType(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Не удалось установить роль для нового пользователя."));
    }
}
