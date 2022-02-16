package com.poseidon.pta.services;

import com.poseidon.pta.domain.DomainElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public abstract class BaseService <E extends DomainElement> {

    private JpaRepository<E, Integer> repository;

    public BaseService(JpaRepository<E, Integer> repository) {
        this.repository = repository;
    }


    private String getType() {
        String className = getClass().getSimpleName().replace("Service","");
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }

    public String home(Model model)
    {
        model.addAttribute(getType() + "s", repository.findAll());
        return getType() + "/list";
    }

    public String addForm(DomainElement e) {
        return getType() + "/add";
    }

    public void dostuff(E e) {
        e.setId(1);
    }

    public String validate(@Valid E e, BindingResult result, Model model) {
        if (!result.hasErrors()){
            repository.save(e);
            model.addAttribute(getType() + "s", repository.findAll());
            return "redirect:/" + getType() + "/list";
        }
        return getType() + "/add";
    }

    public String showUpdateForm(Integer id, Model model) {
        DomainElement e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " + getType() + " id:" + id));
        model.addAttribute(getType(), e);
        return getType() + "/update";
    }

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


    public String delete( Integer id, Model model) {
        E e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " + getType() + " Id:" + id));
        repository.delete(e);
        model.addAttribute(getType() + "s", repository.findAll());
        return "redirect:/" + getType() + "/list";
    }

}
