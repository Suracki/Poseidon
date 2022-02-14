package com.poseidon.pta.jpa;

        import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
        import com.github.springtestdbunit.annotation.DatabaseOperation;
        import com.github.springtestdbunit.annotation.DatabaseSetup;
        import com.poseidon.pta.domain.RuleName;
        import com.poseidon.pta.domain.Trade;
        import com.poseidon.pta.repositories.RuleNameRepository;
        import com.poseidon.pta.repositories.TradeRepository;
        import org.apache.poi.ss.formula.functions.T;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.test.context.TestExecutionListeners;
        import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

        import static org.assertj.core.api.Assertions.assertThat;
        import static org.junit.jupiter.api.Assertions.assertNotNull;
        import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public class TradeJPATests extends BaseJPA {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    void testCreateTradeEntry() {
        assertThat(tradeRepository.findById(1)).isEmpty();

        Trade trade = new Trade();
        trade.setAccount("accOne");
        trade.setType("typeOne");
        trade.setBuyQuantity(new Double(1));
        tradeRepository.save(trade);

        assertNotNull(tradeRepository.findById(1));
    }

    @Test
    @DatabaseSetup(value = {"classpath:trade.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testReadTradeEntry() {
        assertNotNull(tradeRepository.findById(1));
        assertNotNull(tradeRepository.findById(2));
    }


    @Test
    @DatabaseSetup(value = {"classpath:trade.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testUpdateTradeEntry() {
        String beforeAccount = tradeRepository.getById(1).getAccount();

        Trade trade = new Trade();
        trade.setId(1);
        trade.setAccount("accOneTest");
        trade.setType("typeOne");
        trade.setBuyQuantity(new Double(1));
        tradeRepository.save(trade);

        String afterAccount = tradeRepository.getById(1).getAccount();

        assertTrue(beforeAccount.equals("accOne"));
        assertTrue(afterAccount.equals("accOneTest"));
    }

    @Test
    @DatabaseSetup(value = {"classpath:trade.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testDeleteTradeEntry() {
        assertThat(tradeRepository.findById(1)).isNotEmpty();

        Trade trade = new Trade();
        trade.setId(1);
        trade.setAccount("accOne");
        trade.setType("typeOne");
        trade.setBuyQuantity(new Double(1));

        tradeRepository.delete(trade);

        assertThat(tradeRepository.findById(1)).isEmpty();
    }

}
