package com.poseidon.pta.jpa;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.poseidon.pta.domain.CurvePoint;
import com.poseidon.pta.repositories.CurvePointRepository;
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
public class CurvePointJPATests extends BaseJPA {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    void testCreateUserEntry() {
        assertThat(curvePointRepository.findById(1)).isEmpty();

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(new Double(1));
        curvePoint.setValue(new Double(2));
        curvePointRepository.save(curvePoint);

        assertNotNull(curvePointRepository.findById(1));
    }

    @Test
    @DatabaseSetup(value = {"classpath:curvepoint.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testReadUserEntry() {
        assertNotNull(curvePointRepository.findById(1));
        assertNotNull(curvePointRepository.findById(2));
    }


    @Test
    @DatabaseSetup(value = {"classpath:curvepoint.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testUpdateUserEntry() {
        int beforeCurveId = curvePointRepository.getById(1).getCurveId();

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(5);
        curvePoint.setTerm(new Double(1));
        curvePoint.setValue(new Double(2));
        curvePointRepository.save(curvePoint);

        int afterCurveId = curvePointRepository.getById(1).getCurveId();

        assertTrue(beforeCurveId == 3);
        assertTrue(afterCurveId == 5);
    }

    @Test
    @DatabaseSetup(value = {"classpath:curvepoint.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testDeleteUserEntry() {
        assertThat(curvePointRepository.findById(1)).isNotEmpty();

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(3);
        curvePoint.setTerm(new Double(123));
        curvePoint.setValue(new Double(456));

        curvePointRepository.delete(curvePoint);

        assertThat(curvePointRepository.findById(1)).isEmpty();
    }

}
