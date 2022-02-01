package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.User;
import com.poseidon.pta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        return userService.home(model);
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        return userService.validate(user, result, model);
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        return userService.showUpdateForm(id, model);
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        return userService.updateUser(id, user, result, model);
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        return userService.deleteUser(id, model);
    }
}
