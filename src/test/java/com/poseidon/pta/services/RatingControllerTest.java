package com.poseidon.pta.services;

import com.poseidon.pta.domain.Rating;
import com.poseidon.pta.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private static RatingRepository ratingRepository;

    @Test
    @WithMockUser
    public void ratingControllerGetHomeEndpoint() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating/list").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void ratingControllerGetMappingAddBidForm() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating/add").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void ratingControllerPostValidateAddsEntry() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/rating/validate")
                        .param("moodysRating", "1")
                        .param("sandPRating", "2")
                        .param("fitchRating","3")
                        .param("orderNumber","4")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is added to DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(ratingRepository, Mockito.times(1)).save(any(Rating.class));
    }

    @Test
    @WithMockUser
    public void ratingControllerGetUpdateForm() throws Exception {

        Optional<Rating> result = Optional.of(new Rating());
        doReturn(result).when(ratingRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating/update/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void ratingControllerPostUpdate() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/rating/update/1")
                        .param("moodysRating", "1")
                        .param("sandPRating", "2")
                        .param("fitchRating","3")
                        .param("orderNumber","4")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is updated in DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(ratingRepository, Mockito.times(1)).save(any(Rating.class));
    }

    @Test
    @WithMockUser
    public void ratingControllerGetDelete() throws Exception {

        Optional<Rating> result = Optional.of(new Rating());
        doReturn(result).when(ratingRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating/delete/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify entry is removed from DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(ratingRepository, Mockito.times(1)).delete(any(Rating.class));
    }


}

