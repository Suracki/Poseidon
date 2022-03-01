package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.User;
import com.poseidon.pta.security.RoleCheck;
import com.poseidon.pta.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * RestController for /user endpoint
 *
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleCheck roleCheck;

    private static final Logger logger = LogManager.getLogger("UserController");

    /**
     * Mapping for /list
     *
     * Calls userService.home method to populate model and get redirect for list
     *
     * @param model Model object to hold data loaded from repo
     * @return url string
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        logger.info("User connected to /user/list endpoint");

        if (!roleCheck.RoleCheck("ADMIN")) {
            logger.info("User is not an ADMIN, logging out and redirecting");
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return "/home";
        }
        return userService.home(model);
    }

    /**
     * Mapping for GET /add
     *
     * Calls userService.addUser method to get redirect for form to add new User element
     *
     * @param user User object
     * @return url string
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        logger.info("User connected to /user/add endpoint");

        if (!roleCheck.RoleCheck("ADMIN")) {
            logger.info("User is not an ADMIN, logging out and redirecting");
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return "/home";
        }

        return userService.addUser(user);
    }

    /**
     * Mapping for POST /add
     *
     * Calls userService.validate method to validate provided User element
     * Adds element to repo if valid & updates model
     * Returns to add form if not valid
     *
     * @param user User object
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        logger.info("User connected to /user/validate endpoint");

        if (!roleCheck.RoleCheck("ADMIN")) {
            logger.info("User is not an ADMIN, logging out and redirecting");
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return "/home";
        }

        return userService.validate(user, result, model);
    }

    /**
     * Mapping for GET /update/{id}
     *
     * Calls userService.showUpdateForm method to get redirect for form to update existing User element
     *
     * @param id User's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /user/update/ GET endpoint for user with id " + id);

        if (!roleCheck.RoleCheck("ADMIN")) {
            logger.info("User is not an ADMIN, logging out and redirecting");
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return "/home";
        }

        return userService.showUpdateForm(id, model);
    }

    /**
     * Mapping for POST /update/{id}
     *
     * Calls userService.updateUser method to validate provided User element
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id User's ID value
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        logger.info("User connected to /user/update/ POST endpoint for user with id " + id);

        if (!roleCheck.RoleCheck("ADMIN")) {
            logger.info("User is not an ADMIN, logging out and redirecting");
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return "/home";
        }

        return userService.updateUser(id, user, result, model);
    }

    /**
     * Mapping for GET /delete/{id}
     *
     * Calls userService.deleteUser method to delete User element with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id User's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /user/delete/ endpoint for user with id " + id);

        if (!roleCheck.RoleCheck("ADMIN")) {
            logger.info("User is not an ADMIN, logging out and redirecting");
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return "/home";
        }

        return userService.deleteUser(id, model);
    }
}
