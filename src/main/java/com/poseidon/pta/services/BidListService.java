package com.poseidon.pta.services;

import com.poseidon.pta.domain.BidList;
import com.poseidon.pta.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class BidListService extends BaseService<BidList>{
    @Autowired
    private BidListRepository bidListRepository;

    public BidListService() {
        super("bidList");
    }

    public String update(Integer id, BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
