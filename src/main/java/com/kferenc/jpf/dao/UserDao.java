package com.kferenc.jpf.dao;

import com.kferenc.jpf.model.User;

public interface UserDao {

    public User getUser(Long id);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public Long addUser(User user);

    public void updateUser(User user);

    public void removeUser(Long id);

}
