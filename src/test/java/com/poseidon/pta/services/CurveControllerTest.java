package com.poseidon.pta.services;

        import com.poseidon.pta.domain.CurvePoint;
        import com.poseidon.pta.repositories.CurvePointRepository;
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
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private static CurvePointRepository curvePointRepository;

    @Test
    @WithMockUser
    public void curveControllerGetHomeEndpoint() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void curveControllerGetMappingAddBidForm() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/add").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void curveControllerPostValidateAddsEntry() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/curvePoint/validate")
                        .param("curveId", "1")
                        .param("term", "123")
                        .param("value","456")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is added to DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(curvePointRepository, Mockito.times(1)).save(any(CurvePoint.class));
    }

    @Test
    @WithMockUser
    public void curveControllerGetUpdateForm() throws Exception {

        Optional<CurvePoint> result = Optional.of(new CurvePoint());
        doReturn(result).when(curvePointRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertTrue(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @WithMockUser
    public void curveControllerPostUpdate() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                post("/curvePoint/update/1")
                        .param("curveId", "1")
                        .param("term", "123")
                        .param("value","456")
                        .with(csrf())
                        .accept(MediaType.ALL)).andReturn();

        //Verify entry is updated in DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(curvePointRepository, Mockito.times(1)).save(any(CurvePoint.class));
    }

    @Test
    @WithMockUser
    public void curveControllerGetDelete() throws Exception {

        Optional<CurvePoint> result = Optional.of(new CurvePoint());
        doReturn(result).when(curvePointRepository).findById(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        //Verify entry is removed from DB and we are redirected (302)
        assertTrue(mvcResult.getResponse().getStatus() == 302);
        Mockito.verify(curvePointRepository, Mockito.times(1)).delete(any(CurvePoint.class));
    }


}
