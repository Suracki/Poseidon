package com.poseidon.pta.controllers;

import com.poseidon.pta.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for login functions
 *
 */
@Controller
@RequestMapping("app")
public class LoginController {

    private static final Logger logger = LogManager.getLogger("LoginController");

    @Autowired
    private UserRepository userRepository;

    /**
     * Mapping for login
     *
     * Creates and returns ModelAndView object for login
     *
     * @return ModelAndView
     */
    @GetMapping("login")
    public ModelAndView login() {
        logger.info("User connected to login endpoint");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * Mapping for secure/article-details
     *
     * Creates and returns ModelAndView object for users/list containing all Users found in the repo
     *
     * @return ModelAndView
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        logger.info("User connected to login endpoint, accessing user/list");
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Mapping for error
     *
     * Creates and returns ModelAndView object for access attempts by unauthorized users
     * Returns 403 error
     *
     * @return ModelAndView
     */
    @GetMapping("error")
    public ModelAndView error() {
        logger.info("User not authorized");
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
