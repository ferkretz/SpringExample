package com.kferenc.jpf.service;

import com.kferenc.jpf.model.User;

public interface UserService {

    public User getUser(Long id);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public Long addUser(User user);

    public void updateUser(User user);

    public void removeUser(Long id);

}
