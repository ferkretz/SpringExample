package com.kferenc.jpf.controller;

import com.kferenc.jpf.model.DatabaseSetup;
import com.kferenc.jpf.model.LoginForm;
import com.kferenc.jpf.model.User;
import com.kferenc.jpf.service.ActivityService;
import com.kferenc.jpf.service.CurrentUser;
import com.kferenc.jpf.service.DatabaseSetupService;
import com.kferenc.jpf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    DatabaseSetupService databaseSetupService;
    @Autowired
    RoleService roleService;
    @Autowired
    ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET)
    public String indexGet(ModelMap model, @CurrentUser User currentUser) {
        DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();

        if (!databaseSetup.isFullConfigured()) {
            return "redirect:/setup";
        }

        if (currentUser == null) {
            currentUser = new User();
        } else {
            model.addAttribute("activities", activityService.listActivities());
            model.addAttribute("roles", roleService.listRoles());
        }

        model.addAttribute("currentUser", currentUser);

        return "index/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(ModelMap model) {
        model.addAttribute("loginForm", new LoginForm());
        return "index/login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginFailedGet(
            @ModelAttribute(value = "loginForm") LoginForm loginForm,
            BindingResult result, ModelMap model
    ) {
        model.addAttribute("warningCode", "index.error.loginNotFoundLabel");
        model.addAttribute("loginForm", loginForm);

        return "index/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String loginoutGet(ModelMap model) {
        model.addAttribute("informationCode", "index.error.logoutSuccessLabel");
        model.addAttribute("currentUser", new User());

        return "index/index";
    }

}
