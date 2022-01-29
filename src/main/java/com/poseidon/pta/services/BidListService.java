package com.poseidon.pta.services;

import com.poseidon.pta.domain.BidList;
import com.poseidon.pta.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        //done?
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        // done?
        if (!result.hasErrors()){
            bidListRepository.save(bid);
            model.addAttribute("bidLists", bidListRepository.findAll());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    public String showUpdateForm(Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        // done?
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    public String updateBid(Integer id, BidList bidList,
                            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        // done?
        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    public String deleteBid( Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        // done?
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
