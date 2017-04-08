package com.kferenc.jpf.dao;

import com.kferenc.jpf.model.Role;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Role, Long> implements RoleDao {

    @Override
    public Role getRole(Long id) {
        return getByKey(id);
    }

    @Override
    public Role getRoleBySlug(String slug) {
        return getBy("slug", slug);
    }

    @Override
    public Role getRoleByName(String name) {
        return getBy("name", name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listRoles() {
        return createCriteria().list();
    }

    @Override
    public Long addRole(Role role) {
        return save(role);
    }

    @Override
    public void updateRole(Role role) {
        update(role);
    }

    @Override
    public void removeRole(Long id) {
        delete(getByKey(id));
    }

}
