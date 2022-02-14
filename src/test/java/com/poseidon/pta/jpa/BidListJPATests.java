package com.poseidon.pta.jpa;

        import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
        import com.github.springtestdbunit.annotation.DatabaseOperation;
        import com.github.springtestdbunit.annotation.DatabaseSetup;
        import com.poseidon.pta.domain.BidList;
        import com.poseidon.pta.repositories.BidListRepository;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.test.context.TestExecutionListeners;
        import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

        import static org.assertj.core.api.Assertions.assertThat;
        import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public class BidListJPATests extends BaseJPA{

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    void testCreateBidEntry() {
        assertThat(bidListRepository.findById(1)).isEmpty();

        BidList testBidList = new BidList();
        testBidList.setAccount("TestAccount");
        testBidList.setType("TestType");
        testBidList.setBidQuantity(new Double(123));
        bidListRepository.save(testBidList);

        assertNotNull(bidListRepository.findById(1));
    }

    @Test
    @DatabaseSetup(value = {"classpath:bidlist.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testReadBidEntry() {
        assertNotNull(bidListRepository.findById(1));
        assertNotNull(bidListRepository.findById(2));
    }


    @Test
    @DatabaseSetup(value = {"classpath:bidlist.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testUpdateBidEntry() {
        String beforeAccount = bidListRepository.getById(1).getAccount();

        BidList testBidList = new BidList();
        testBidList.setId(1);
        testBidList.setAccount("TestAccountThree");
        testBidList.setType("TestType");
        testBidList.setBidQuantity(new Double(123));
        bidListRepository.save(testBidList);

        String afterAccount = bidListRepository.getById(1).getAccount();

        assertTrue(beforeAccount.equals("TestAccount"));
        assertTrue(afterAccount.equals("TestAccountThree"));
    }

    @Test
    @DatabaseSetup(value = {"classpath:bidlist.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testDeleteBidEntry() {
        assertThat(bidListRepository.findById(1)).isNotEmpty();

        BidList testBidList = new BidList();
        testBidList.setId(1);
        testBidList.setAccount("TestAccount");
        testBidList.setType("TestType");
        testBidList.setBidQuantity(new Double(123));
        bidListRepository.delete(testBidList);

        assertThat(bidListRepository.findById(1)).isEmpty();

    }

}
