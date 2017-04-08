package com.kferenc.jpf.controller;

import com.kferenc.jpf.model.DatabaseSetup;
import com.kferenc.jpf.model.SetupAdminForm;
import com.kferenc.jpf.model.SetupDatabaseForm;
import com.kferenc.jpf.model.User;
import com.kferenc.jpf.service.DatabaseSetupService;
import com.kferenc.jpf.service.UserService;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    DatabaseSetupService databaseSetupService;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String databaseGet(ModelMap model) {
        DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();

        if (databaseSetup.isSchemeCreated()) {
            return "redirect:/setup/admin";
        }

        SetupDatabaseForm setupDatabaseForm = new SetupDatabaseForm();
        setupDatabaseForm.setCatalog(databaseSetup.getCatalog());
        setupDatabaseForm.setHost(databaseSetup.getHost());
        setupDatabaseForm.setPort(databaseSetup.getPort());
        setupDatabaseForm.setUsername(databaseSetup.getUsername());
        setupDatabaseForm.setPassword(databaseSetup.getPassword());
        model.addAttribute("setupDatabaseForm", setupDatabaseForm);

        return "setup/database";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String databasePost(
            @ModelAttribute(value = "setupDatabaseForm") SetupDatabaseForm setupDatabaseForm,
            BindingResult result, ModelMap model
    ) {
        if (!result.hasErrors()) {
            DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();
            databaseSetup.setCatalog(setupDatabaseForm.getCatalog());
            databaseSetup.setHost(setupDatabaseForm.getHost());
            databaseSetup.setPort(setupDatabaseForm.getPort());
            databaseSetup.setUsername(setupDatabaseForm.getUsername());
            databaseSetup.setPassword(setupDatabaseForm.getPassword());
            databaseSetupService.setDatabaseSetup(databaseSetup);

            try {
                databaseSetupService.prepareDatabase(databaseSetup, "classpath:/scheme.sql");

                return "redirect:/setup/admin";
            } catch (DataAccessException ex) {
                Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);

                model.addAttribute("errorCode", "setup.error.databaseConnectLabel");
                model.addAttribute("setupDatabaseForm", setupDatabaseForm);

                return "setup/database";
            } catch (IOException ex) {
                Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);

                model.addAttribute("errorCode", "setup.error.configAccessLabel");
                model.addAttribute("setupDatabaseForm", setupDatabaseForm);

                return "setup/database";
            }
        } else {
            model.addAttribute("errorCode", "setup.error.httpRequestLabel");
            model.addAttribute("setupDatabaseForm", setupDatabaseForm);

            return "setup/database";
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminGet(ModelMap model) {
        DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();

        if (databaseSetup.isNone()) {
            return "redirect:/setup";
        }

        if (databaseSetup.isFullConfigured()) {
            model.addAttribute("warningCode", "setup.error.databaseConfiguredLabel");
            return "redirect:/";
        }

        SetupAdminForm setupAdminForm = new SetupAdminForm();
        setupAdminForm.setUsername("Administrator");

        model.addAttribute("setupAdminForm", setupAdminForm);

        return "setup/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String adminPost(
            @Valid @ModelAttribute(value = "setupAdminForm") SetupAdminForm setupAdminForm,
            BindingResult result, ModelMap model
    ) {
        DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();

        if (!setupAdminForm.getDatabasePassword().equals(databaseSetup.getPassword())) {
            model.addAttribute("errorCode", "setup.error.wrongDatabasePasswordLabel");
            model.addAttribute("setupAdminForm", setupAdminForm);

            return "setup/admin";
        }

        if (!result.hasErrors()) {
            try {
                User administrator = new User();
                administrator.setUsername(setupAdminForm.getUsername());
                administrator.setDisplayName(setupAdminForm.getUsername());
                administrator.setPassword(setupAdminForm.getPassword());
                administrator.setEnabled(true);
                administrator.setRegisteredDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
                administrator.setRoleIds(new Long[]{1L, 2L, 3L});
                userService.addUser(administrator);

                databaseSetup.setFullConfigured();
                databaseSetupService.setDatabaseSetup(databaseSetup);
                databaseSetupService.storeDatabaseSetup(databaseSetup);
                return "redirect:/";
            } catch (DataAccessException ex) {
                Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);

                model.addAttribute("errorCode", "setup.error.databaseConnectLabel");
                model.addAttribute("setupAdminForm", setupAdminForm);

                return "setup/admin";
            } catch (IOException ex) {
                Logger.getLogger(SetupController.class.getName()).log(Level.SEVERE, null, ex);

                model.addAttribute("errorCode", "setup.error.configAccessLabel");
                model.addAttribute("setupAdminForm", setupAdminForm);

                return "setup/admin";
            }
        } else {
            model.addAttribute("errorCode", "setup.error.httpRequestLabel");
            model.addAttribute("setupAdminForm", setupAdminForm);

            return "setup/admin";
        }
    }

}
