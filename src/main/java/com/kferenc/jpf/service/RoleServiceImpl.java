package com.kferenc.jpf.service;

import com.kferenc.jpf.dao.RoleDao;
import com.kferenc.jpf.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Override
    public Role getRoleBySlug(String slug) {
        return roleDao.getRoleBySlug(slug);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    public Long addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void removeRole(Long id) {
        roleDao.removeRole(id);
    }

}
