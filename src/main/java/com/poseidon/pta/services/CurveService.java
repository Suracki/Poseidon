package com.poseidon.pta.services;

import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class CurveService extends BaseService<CurvePoint>{
    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurveService() {
        super("curvePoint");
    }

    public String update(Integer id, CurvePoint curve,
                                  BindingResult result, Model model){
        if (result.hasErrors()) {
            return "curvePoint/update";
        }

        curve.setId(id);
        curvePointRepository.save(curve);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        return "redirect:/curvePoint/list";
    }

}
