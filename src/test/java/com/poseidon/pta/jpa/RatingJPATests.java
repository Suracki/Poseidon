package com.poseidon.pta.jpa;

        import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
        import com.github.springtestdbunit.annotation.DatabaseOperation;
        import com.github.springtestdbunit.annotation.DatabaseSetup;
        import com.poseidon.pta.domain.Rating;
        import com.poseidon.pta.repositories.RatingRepository;
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
public class RatingJPATests extends BaseJPA {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void testCreateUserEntry() {
        assertThat(ratingRepository.findById(1)).isEmpty();

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(2);
        ratingRepository.save(rating);

        assertNotNull(ratingRepository.findById(1));
    }

    @Test
    @DatabaseSetup(value = {"classpath:rating.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testReadUserEntry() {
        assertNotNull(ratingRepository.findById(1));
        assertNotNull(ratingRepository.findById(2));
    }


    @Test
    @DatabaseSetup(value = {"classpath:rating.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testUpdateUserEntry() {
        int beforeOrderNumber = ratingRepository.getById(1).getOrderNumber();

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(7);
        ratingRepository.save(rating);

        int afterOrderNumber = ratingRepository.getById(1).getOrderNumber();

        assertTrue(beforeOrderNumber == 3);
        assertTrue(afterOrderNumber == 7);
    }

    @Test
    @DatabaseSetup(value = {"classpath:rating.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testDeleteUserEntry() {
        assertThat(ratingRepository.findById(1)).isNotEmpty();

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("moodyOne");
        rating.setSandPRating("sandOne");
        rating.setFitchRating("fitchOne");
        rating.setOrderNumber(3);
        ratingRepository.delete(rating);

        assertThat(ratingRepository.findById(1)).isEmpty();
    }

}

