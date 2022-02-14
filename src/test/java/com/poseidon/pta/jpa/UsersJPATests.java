package com.poseidon.pta.jpa;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.poseidon.pta.domain.User;
import com.poseidon.pta.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public class UsersJPATests extends BaseJPA {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUserEntry() {
        assertThat(userRepository.findByUsername("TestUserNameNew")).isNull();

        User testUser = new User();
        testUser.setFullname("TestFullNameNew");
        testUser.setUsername("TestUserNameNew");
        testUser.setRole("ADMIN");
        testUser.setPassword("$2a$12$YU1k9gDedghY2bUq1ya/se18F4lTrJvhtxDRxkT8MZM44DoYVisWG");
        userRepository.save(testUser);

        assertNotNull(userRepository.findByUsername("TestUserNameNew"));
    }

    @Test
    @DatabaseSetup(value = {"classpath:users.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testReadUserEntry() {
        assertNotNull(userRepository.findByUsername("TestUserName"));
        assertNotNull(userRepository.findByUsername("TestUserNameTwo"));
    }


    @Test
    @DatabaseSetup(value = {"classpath:users.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testUpdateUserEntry() {
        assertNotNull(userRepository.findByUsername("TestUserName"));

        User testUser = new User();
        testUser.setId(1);
        testUser.setFullname("TestFullNameNew");
        testUser.setUsername("TestUserNameNew");
        testUser.setRole("ADMIN");
        testUser.setPassword("$2a$12$YU1k9gDedghY2bUq1ya/se18F4lTrJvhtxDRxkT8MZM44DoYVisWG");
        userRepository.save(testUser);

        assertNull(userRepository.findByUsername("TestUserName"));
        assertNotNull(userRepository.findByUsername("TestUserNameNew"));
    }

    @Test
    @DatabaseSetup(value = {"classpath:users.xml"}, type = DatabaseOperation.CLEAN_INSERT)
    void testDeleteUserEntry() {
        assertNotNull(userRepository.findByUsername("TestUserName"));

        User testUser = new User();
        testUser.setId(1);
        testUser.setFullname("TestFullName");
        testUser.setUsername("TestUserName");
        testUser.setRole("ADMIN");
        testUser.setPassword("$2a$12$YU1k9gDedghY2bUq1ya/se18F4lTrJvhtxDRxkT8MZM44DoYVisWG");
        userRepository.delete(testUser);

        assertNull(userRepository.findByUsername("TestUserName"));
    }

}
