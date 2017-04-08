package com.kferenc.jpf.dao;

import com.kferenc.jpf.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Override
    public User getUser(Long id) {
        return getByKey(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return getBy("username", username);
    }

    @Override
    public User getUserByEmail(String email) {
        return getBy("email", email);
    }

    @Override
    public Long addUser(User user) {
        return save(user);
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public void removeUser(Long id) {
        delete(getByKey(id));
    }

}
