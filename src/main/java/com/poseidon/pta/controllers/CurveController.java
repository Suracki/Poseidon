package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.services.BidListService;
import com.poseidon.pta.services.CurveService;
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
public class CurveController {
    @Autowired
    private CurveService curveService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        return curveService.home(model);
    }

    @GetMapping("/curvePoint/add")
    public String addForm(CurvePoint curvePoint) {
        return curveService.addForm(curvePoint);
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        return curveService.validate(curvePoint, result, model);
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        return curveService.showUpdateForm(id, model);
    }

    @PostMapping("/curvePoint/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        return curveService.update(id, curvePoint, result, model);
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        return curveService.delete(id, model);
    }
}
