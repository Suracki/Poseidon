package com.poseidon.pta.services;

import com.poseidon.pta.domain.DomainElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

/**
 * BaseService provides methods for individual element services to perform operations for their controller's endpoints
 *
 * BaseService learns it's type from the class name, and requires the exending class to provide it an appropriate repo
 */
public abstract class BaseService <E extends DomainElement> {

    private JpaRepository<E, Integer> repository;

    public BaseService(JpaRepository<E, Integer> repository) {
        this.repository = repository;
    }


    private String getType() {
        String className = getClass().getSimpleName().replace("Service","");
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }

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
        model.addAttribute(getType() + "s", repository.findAll());
        return getType() + "/list";
    }

    /**
     * Method to get redirect for form to add a new element
     *
     * @param e DomainElement object of type to be added
     * @return url String
     */
    public String addForm(DomainElement e) {
        return getType() + "/add";
    }

    /**
     * Method to validate provided DomainElement
     * Adds DomainElement to repository if valid & updates model
     * Returns to form if any errors found
     *
     * @param e DomainElement object to be added
     * @param result BindingResult for validation
     * @param model Model object
     * @return url String
     */
    public String validate(@Valid E e, BindingResult result, Model model) {
        if (!result.hasErrors()){
            repository.save(e);
            model.addAttribute(getType() + "s", repository.findAll());
            return "redirect:/" + getType() + "/list";
        }
        return getType() + "/add";
    }

    /**
     * Method to get redirect for form to update existing DomainElement
     * Verifies that privided ID does match an element in the repo
     * Then returns url to update form
     *
     * @param id DomainElement's ID value
     * @param model Model object
     * @return url string
     */
    public String showUpdateForm(Integer id, Model model) {
        DomainElement e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " + getType() + " id:" + id));
        model.addAttribute(getType(), e);
        return getType() + "/update";
    }

    /**
     * Method to validate provided DomainElement
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id DomainElement's ID value
     * @param e DomainElement with updated fields
     * @param result BindingResult for validation
     * @param model Model object
     * @return url string
     */
    public String update(Integer id, E e,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getType() + "/update";
        }

        e.setId(id);
        repository.save(e);
        model.addAttribute(getType() + "s", repository.findAll());
        return "redirect:/" + getType() + "/list";
    }

    /**
     * Method to delete DomainElement with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id DomainElement's ID value
     * @param model Model object
     * @return url string
     */
    public String delete( Integer id, Model model) {
        E e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " + getType() + " Id:" + id));
        repository.delete(e);
        model.addAttribute(getType() + "s", repository.findAll());
        return "redirect:/" + getType() + "/list";
    }

}
