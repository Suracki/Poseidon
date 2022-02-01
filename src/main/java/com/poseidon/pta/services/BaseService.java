package com.poseidon.pta.services;

import com.poseidon.pta.domain.BidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public abstract class BaseService<Element> {

    @Autowired
    private JpaRepository<Element, Integer> repository;
    private String type;

    public BaseService(String type) {
        this.type = type;
    }


    public String home(Model model)
    {
        model.addAttribute(type + "s", repository.findAll());
        return type + "/list";
    }

    public String addForm(BidList bid) {
        return type + "/add";
    }

    public String validate(@Valid Element e, BindingResult result, Model model) {
        if (!result.hasErrors()){
            repository.save(e);
            model.addAttribute(type + "s", repository.findAll());
            return "redirect:/" + type + "/list";
        }
        return type + "/add";
    }

    public String showUpdateForm(Integer id, Model model) {
        Element e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " + type + " id:" + id));
        model.addAttribute(type, e);
        return type + "/update";
    }

    public abstract String update(Integer id, Element e,
                            BindingResult result, Model model);

    public String delete( Integer id, Model model) {
        Element e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +type + " Id:" + id));
        repository.delete(e);
        model.addAttribute(type + "s", repository.findAll());
        return "redirect:/" + type + "/list";
    }

}
