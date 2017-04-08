package com.kferenc.jpf.dao;

import com.kferenc.jpf.model.Role;
import java.util.List;

public interface RoleDao {

    public Role getRole(Long id);

    public Role getRoleBySlug(String slug);

    public Role getRoleByName(String name);

    public List<Role> listRoles();

    public Long addRole(Role role);

    public void updateRole(Role role);

    public void removeRole(Long id);

}
