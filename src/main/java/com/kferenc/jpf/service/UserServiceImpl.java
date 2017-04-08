package com.kferenc.jpf.service;

import com.kferenc.jpf.dao.UserDao;
import com.kferenc.jpf.model.User;
import java.sql.Timestamp;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Long addUser(User user) {
        user.setRegisteredDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

}
