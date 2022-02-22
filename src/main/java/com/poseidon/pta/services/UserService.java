package com.poseidon.pta.services;

import com.poseidon.pta.domain.User;
import com.poseidon.pta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * UserService performs operations for the UserController endpoints
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to populate Model for frontend
     * Obtains all elements of this type from repository and adds to model
     * Then returns redirect to list url
     *
     * @param model Model object to hold data loaded from repo
     * @return redirect url String
     */
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * Method to get redirect for form to add a new element
     *
     * @param user User object of type to be added
     * @return url String
     */
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Method to validate provided User
     * Adds User to repository if valid & updates model
     * Returns to form if any errors found
     *
     * @param user User object to be added
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url String
     */
    public String validate(User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Method to get redirect for form to update existing User
     * Verifies that privided ID does match an element in the repo
     * Then returns url to update form
     *
     * @param id User's ID value
     * @param model Model object
     * @return url string
     */
    public String showUpdateForm(Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Method to validate provided User
     * Updates existing User in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id User's ID value
     * @param user User with updated fields
     * @param result BindingResult for validation
     * @param model Model object
     * @return url string
     */
    public String updateUser(Integer id, User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Method to delete User with provided ID
     * Deletes existing User in repo if exists & updates model
     *
     * @param id User's ID value
     * @param model Model object
     * @return url string
     */
    public String deleteUser(Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

}
