package com.poseidon.pta.services;

import com.poseidon.pta.domain.User;
import com.poseidon.pta.repositories.UserRepository;
import com.poseidon.pta.security.SecurityUserDetailsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private static UserRepository userRepository;

    @InjectMocks
    private static UserService userService;

    @Autowired
    private static SecurityUserDetailsService userDetailsService;

    private static User testAdmin;

    private static User testUser;

    @BeforeAll
    private static void setUpUser(){
        testAdmin = new User();
        testAdmin.setFullname("NameAdmin");
        testAdmin.setRole("ADMIN");
        testAdmin.setUsername("UsernameAdmin");
        testAdmin.setPassword("PASSWORD");

        testUser = new User();
        testUser.setFullname("NameUser");
        testUser.setRole("USER");
        testUser.setUsername("UsernameUser");
        testUser.setPassword("PASSWORD");
    }

    @Test
    @WithMockUser(username="admin")
    public void userControllerGetHomeEndpoint() throws Exception {

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(testAdmin);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/list").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify admin user is able to access user list page (code 200)
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("User List"));
    }

    @Test
    @WithMockUser(username="user")
    public void userControllerGetHomeEndpointBadAccess() throws Exception {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/list").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify non-admin user is forwarded back to home page (code 200)
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HOME PAGE"));
    }

    @Test
    @WithMockUser(username="admin")
    public void userControllerGetMappingAddBidForm() throws Exception {

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(testAdmin);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/add").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify admin user is able to access add user page (code 200)
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Add New User"));
    }

    @Test
    @WithMockUser(username="user")
    public void userControllerGetMappingAddBidFormBadAccess() throws Exception {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/add").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify non-admin user is forwarded back to home page with code (200)
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HOME PAGE"));
    }

    @Test
    @WithMockUser(username="admin")
    public void userControllerPostValidateAddsEntry() throws Exception {

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(testAdmin);

        MvcResult mvcResult = mockMvc.perform(
                post("/user/validate")
                        .param("fullname", "fullname")
                        .param("username", "username")
                        .param("password","TestPassword123$%^")
                        .param("role", "ADMIN")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is added to DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    @WithMockUser(username="user")
    public void userControllerPostValidateAddsEntryBadAccess() throws Exception {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(
                post("/user/validate")
                        .param("fullname", "fullname")
                        .param("username", "username")
                        .param("password","TestPassword123$%^")
                        .param("role", "ADMIN")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();


        //Verify non-admin user is forwarded back to home page with code (200) and no changes are made to repo
        Mockito.verify(userRepository, Mockito.times(0)).save(any(User.class));
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HOME PAGE"));
    }

    @Test
    @WithMockUser(username="admin")
    public void userControllerGetUpdateForm() throws Exception {

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(testAdmin);

        Optional<User> result = Optional.of(new User());
        doReturn(result).when(userRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/update/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser(username="user")
    public void userControllerGetUpdateFormBadAccess() throws Exception {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(testUser);

        Optional<User> result = Optional.of(new User());
        doReturn(result).when(userRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/update/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify non-admin user is forwarded back to home page with code (200)
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HOME PAGE"));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void userControllerPostUpdate() throws Exception {

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(testAdmin);

        MvcResult mvcResult = mockMvc.perform(
                post("/user/update/1")
                        .param("fullname", "fullname")
                        .param("username", "username")
                        .param("password","TestPassword123$%^")
                        .param("role", "ADMIN")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is updated in DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    @WithMockUser(username="user")
    public void userControllerPostUpdateBadAccess() throws Exception {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(
                post("/user/update/1")
                        .param("fullname", "fullname")
                        .param("username", "username")
                        .param("password","TestPassword123$%^")
                        .param("role", "ADMIN")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify non-admin user is forwarded back to home page with code (200) and no changes are made to repo
        Mockito.verify(userRepository, Mockito.times(0)).save(any(User.class));
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HOME PAGE"));
    }

    @Test
    @WithMockUser(username="admin")
    public void userControllerGetDelete() throws Exception {

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(testAdmin);

        Optional<User> result = Optional.of(new User());
        doReturn(result).when(userRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/delete/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify entry is removed from DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(userRepository, Mockito.times(1)).delete(any(User.class));
    }

    @Test
    @WithMockUser(username="user")
    public void userControllerGetDeleteBadAccess() throws Exception {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(testUser);

        Optional<User> result = Optional.of(new User());
        doReturn(result).when(userRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/delete/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify non-admin user is forwarded back to home page with code (200) and no changes are made to repo
        Mockito.verify(userRepository, Mockito.times(0)).delete(any(User.class));
        assertTrue(mvcResult.getResponse().getStatus() == 200);
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HOME PAGE"));
    }


}




