package com.kferenc.jpf;

import com.kferenc.jpf.configuration.PenguinForumConfig;
import com.kferenc.jpf.configuration.SpringMvcInitializer;
import com.kferenc.jpf.model.DatabaseSetup;
import com.kferenc.jpf.model.User;
import com.kferenc.jpf.service.DatabaseSetupService;
import com.kferenc.jpf.service.UserService;
import java.io.IOException;
import org.hibernate.HibernateException;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    SpringMvcInitializer.class,
    PenguinForumConfig.class,})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    DatabaseSetupService databaseSetupService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    /*
      Manual test properties in 'application.properties' file
        jdbc.configured = # must be empty
        jdbc.username   = # username of the database (usually root)
        jdbc.password   = # password for userame
        jdbc.port       = # optional (usually 3306)
        jdbc.catalog    = # database name (default: jpf)
     */
    // Configuration test will be DELETE your existed database!!!
    @Test
    public void test0_configurationTest() throws DataAccessException, IOException {
        DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();
        databaseSetup.setNone();
        databaseSetupService.prepareDatabase(databaseSetup, "classpath:/scheme.sql");
    }

    @Test
    public void test1_addUser() {
        System.out.println("*** Running 'addUser' test... ***");

        User user = new User();
        user.setUsername("Username");
        user.setDisplayName("DisplayName");
        user.setPassword("Password");
        user.setEmail("email@email.hu");
        user.setEnabled(true);
        user.setRoleIds(new Long[]{1L, 2L, 3L});
        userService.addUser(user);
    }

    @Test(expected = HibernateException.class)
    public void test2_addUserWithSameEmail() {
        System.out.println("*** Running 'addUserWithSameEmail' test... ***");

        User user = new User();
        user.setUsername("Username2");
        user.setDisplayName("DisplayName2");
        user.setPassword("Password");
        user.setEmail("email@email.hu");
        user.setEnabled(true);
        user.setRoleIds(new Long[]{1L, 2L, 3L});
        userService.addUser(user);
    }

    @Test
    public void test3_testPassword() {
        System.out.println("*** Running 'testPassword' test... ***");

        User user = userService.getUserByUsername("Username");
        assertTrue(passwordEncoder.matches("Password", user.getPassword()));
    }

    @Test
    public void test4_changeDisplayName() {
        System.out.println("*** Running 'changeDisplayName' test... ***");

        User user = userService.getUserByUsername("Username");
        user.setDisplayName("Other DisplayName");
        System.out.println(user.getDisplayName());
        userService.updateUser(user);
    }

}
