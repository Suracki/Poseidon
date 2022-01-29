package com.poseidon.pta.services;

import com.poseidon.pta.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class CurveService extends BaseService<CurvePoint>{

    private JpaRepository<CurvePoint, Integer> repository;
    private String type;

    public CurveService() {
        super("Curve");
    }

    public String update(Integer id, CurvePoint curve,
                                  BindingResult result, Model model){
        if (result.hasErrors()) {
            return "bidList/update";
        }

        curve.setId(id);
        repository.save(curve);
        model.addAttribute("bidLists", repository.findAll());
        return "redirect:/bidList/list";
    }

}
