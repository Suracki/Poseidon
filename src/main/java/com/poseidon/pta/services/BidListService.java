package com.poseidon.pta.services;

import com.poseidon.pta.domain.BidList;
import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Service
public class BidListService extends BaseService<BidList>{
    @Autowired
    private BidListRepository bidListRepository;

    public BidListService() {
        super("bidList");
    }

//    public String home(Model model)
//    {
//        model.addAttribute("bidLists", bidListRepository.findAll());
//        return "bidList/list";
//    }
//
//    public String addBidForm(BidList bid) {
//        return "bidList/add";
//    }
//
//    public String validate(@Valid BidList bid, BindingResult result, Model model) {
//        if (!result.hasErrors()){
//            bidListRepository.save(bid);
//            model.addAttribute("bidLists", bidListRepository.findAll());
//            return "redirect:/bidList/list";
//        }
//        return "bidList/add";
//    }
//
//    public String showUpdateForm(Integer id, Model model) {
//        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList id:" + id));
//        model.addAttribute("bidList", bidList);
//        return "bidList/update";
//    }

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

//    public String deleteBid( Integer id, Model model) {
//        System.out.println("Attempting to delete bid id: " + id);
//        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
//        System.out.println("Found bidList with id: " + bidList.getBidListId());
//        bidListRepository.delete(bidList);
//        model.addAttribute("bidLists", bidListRepository.findAll());
//        return "redirect:/bidList/list";
//    }
}
