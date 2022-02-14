package com.poseidon.pta.jpa;

        import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
        import com.github.springtestdbunit.annotation.DatabaseOperation;
        import com.github.springtestdbunit.annotation.DatabaseSetup;
        import com.poseidon.pta.domain.RuleName;
        import com.poseidon.pta.repositories.RuleNameRepository;
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
public class RuleNameJPATests extends BaseJPA {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    void testCreateUserEntry() {
        assertThat(ruleNameRepository.findById(1)).isEmpty();

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setTemplate("temp");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        assertNotNull(ruleNameRepository.findById(1));
    }

    @Test
    @DatabaseSetup(value = {"classpath:rulename.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testReadUserEntry() {
        assertNotNull(ruleNameRepository.findById(1));
        assertNotNull(ruleNameRepository.findById(2));
    }


    @Test
    @DatabaseSetup(value = {"classpath:rulename.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testUpdateUserEntry() {
        String beforeName = ruleNameRepository.getById(1).getName();

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setTemplate("temp");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        String afterName = ruleNameRepository.getById(1).getName();

        assertTrue(beforeName.equals("rulenameone"));
        assertTrue(afterName.equals("name"));
    }

    @Test
    @DatabaseSetup(value = {"classpath:rulename.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testDeleteUserEntry() {
        assertThat(ruleNameRepository.findById(1)).isNotEmpty();

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("rulenameone");
        ruleName.setDescription("descone");
        ruleName.setJson("jsonone");
        ruleName.setTemplate("tempone");
        ruleName.setSqlStr("strone");
        ruleName.setSqlPart("partone");

        ruleNameRepository.delete(ruleName);

        assertThat(ruleNameRepository.findById(1)).isEmpty();
    }

}
