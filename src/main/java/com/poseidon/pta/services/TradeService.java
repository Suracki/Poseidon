package com.poseidon.pta.services;

import com.poseidon.pta.domain.Trade;
import com.poseidon.pta.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class TradeService extends BaseService<Trade>{
    @Autowired
    private TradeRepository tradeRepository;

    public TradeService() {
        super("trade");
    }

    public String update(Integer id, Trade trade,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }

        trade.setTradeId(id);
        tradeRepository.save(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        return "redirect:/trade/list";
    }
}
