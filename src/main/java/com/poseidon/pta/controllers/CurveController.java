package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.services.CurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * RestController for /curvePoint endpoint
 *
 */
@Controller
public class CurveController {
    @Autowired
    private CurvePointService curvePointService;

    private static final Logger logger = LogManager.getLogger("CurveController");

    /**
     * Mapping for /list
     *
     * Calls curvePointService.home method to populate model and get redirect for list
     *
     * @param model Model object to hold data loaded from repo
     * @return url string
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        logger.info("User connected to /curvePoint/list endpoint");
        return curvePointService.home(model);
    }

    /**
     * Mapping for GET /add
     *
     * Calls curvePointService.addForm method to get redirect for form to add new CurvePoint element
     *
     * @param curvePoint CurvePoint object
     * @return url string
     */
    @GetMapping("/curvePoint/add")
    public String addForm(CurvePoint curvePoint) {
        logger.info("User connected to /curvePoint/add endpoint");
        return curvePointService.addForm(curvePoint);
    }

    /**
     * Mapping for POST /add
     *
     * Calls curvePointService.validate method to validate provided CurvePoint element
     * Adds element to repo if valid & updates model
     * Returns to add form if not valid
     *
     * @param curvePoint CurvePoint object
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        logger.info("User connected to /curvePoint/validate endpoint");
        return curvePointService.validate(curvePoint, result, model);
    }

    /**
     * Mapping for GET /update/{id}
     *
     * Calls curvePointService.showUpdateForm method to get redirect for form to update existing CurvePoint element
     *
     * @param id CurvePoint's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /curvePoint/update/ GET endpoint for curvePoint with id " + id);
        return curvePointService.showUpdateForm(id, model);
    }

    /**
     * Mapping for POST /update/{id}
     *
     * Calls curvePointService.update method to validate provided CurvePoint element
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id CurvePoint's ID value
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/curvePoint/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        logger.info("User connected to /curvePoint/update/ POST endpoint for curvePoint with id " + id);
        return curvePointService.update(id, curvePoint, result, model);
    }

    /**
     * Mapping for GET /delete/{id}
     *
     * Calls curvePointService.delete method to delete CurvePoint element with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id CurvePoint's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /curvePoint/delete/ endpoint for curvePoint with id " + id);
        return curvePointService.delete(id, model);
    }
}
