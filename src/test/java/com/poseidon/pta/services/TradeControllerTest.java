package com.poseidon.pta.services;

import com.poseidon.pta.domain.RuleName;
import com.poseidon.pta.domain.Trade;
import com.poseidon.pta.repositories.RuleNameRepository;
import com.poseidon.pta.repositories.TradeRepository;
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
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private static TradeRepository tradeRepository;

    @Test
    @WithMockUser
    public void tradeControllerGetHomeEndpoint() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trade/list").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void tradeControllerGetMappingAddBidForm() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trade/add").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void tradeControllerPostValidateAddsEntry() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/trade/validate")
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity","123")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is added to DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(tradeRepository, Mockito.times(1)).save(any(Trade.class));
    }

    @Test
    @WithMockUser
    public void tradeControllerGetUpdateForm() throws Exception {

        Optional<Trade> result = Optional.of(new Trade());
        doReturn(result).when(tradeRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trade/update/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void tradeControllerPostUpdate() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/trade/update/1")
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity","123")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is updated in DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(tradeRepository, Mockito.times(1)).save(any(Trade.class));
    }

    @Test
    @WithMockUser
    public void tradeControllerGetDelete() throws Exception {

        Optional<Trade> result = Optional.of(new Trade());
        doReturn(result).when(tradeRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify entry is removed from DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(tradeRepository, Mockito.times(1)).delete(any(Trade.class));
    }


}



