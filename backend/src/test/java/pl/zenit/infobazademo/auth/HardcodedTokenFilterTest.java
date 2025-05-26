package pl.zenit.infobazademo.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HardcodedTokenFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testApi_withValidToken_shouldBeOk() throws Exception {
        mockMvc.perform(get("/roster")
                        .header("Authorization", "Bearer " + HardcodedTokenFilter.TEST_TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    void testApi_withBadToken_shouldBe401() throws Exception {
        mockMvc.perform(get("/roster")
                        .header("Authorization", "Bearer invalid"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testApi_withNoToken_shouldBe401() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testOnlineTest_withNoToken_shouldBeOK() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void testOnlineTest_withBadToken_shouldBeOK() throws Exception {
        mockMvc.perform(get("/")
                        .header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isOk());
    }

}