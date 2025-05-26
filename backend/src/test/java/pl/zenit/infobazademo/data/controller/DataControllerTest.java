package pl.zenit.infobazademo.data.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.zenit.infobazademo.auth.HardcodedTokenFilter;
import pl.zenit.infobazademo.data.cache.DataCache;
import pl.zenit.infobazademo.data.domain.Geo;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DataControllerTest {
    private static pl.zenit.infobazademo.data.domain.User domainUser1;
    private static pl.zenit.infobazademo.data.domain.User domainUser2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataCache dataCache;

    @BeforeAll
    static void beforeAll() {
        pl.zenit.infobazademo.data.domain.Address domainAddress = new pl.zenit.infobazademo.data.domain.Address(
                "Ellsworth Summit",
                "Suite 729",
                "Aliyaview",
                "45169",
                new Geo("0", "0")
        );

        pl.zenit.infobazademo.data.domain.Company domainCompany = new pl.zenit.infobazademo.data.domain.Company(
                "Abernathy Group",
                "Implemented secondary concept",
                "e-enable extensible e-tailers"
        );

        domainUser1 = new pl.zenit.infobazademo.data.domain.User(
                8,
                "Nicholas Runolfsdottir V",
                "Maxime_Nienow",
                "Sherwood@rosamond.me",
                domainAddress,
                "586.493.6943 x140",
                "jacynthe.com",
                domainCompany
        );


        pl.zenit.infobazademo.data.domain.Address domainAddress2 = new pl.zenit.infobazademo.data.domain.Address(
                "-",
                "-",
                "-",
                "-",
                new pl.zenit.infobazademo.data.domain.Geo("-", "-")
        );

        pl.zenit.infobazademo.data.domain.Company domainCompany2 = new pl.zenit.infobazademo.data.domain.Company(
                "-",
                "-",
                "-"
        );

        domainUser2 = new pl.zenit.infobazademo.data.domain.User(
                9,
                "Glenna Reichert",
                "Delphine",
                "Chaim_McDermott@dana.io",
                domainAddress2,
                "(775)976-6794 x41206",
                "-",
                domainCompany2
        );

    }

    @Test
    void testGetRosterUsers_whenEmpty_shouldReturnEmptyList() throws Exception {
        when(dataCache.getUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/" + domainUser1.id())
                        .header("Authorization", "Bearer " + HardcodedTokenFilter.TEST_TOKEN))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetRosterUsers_whenPopulated_shouldReturnCorrectList() throws Exception {
        when(dataCache.getUsers()).thenReturn(List.of(domainUser1, domainUser2));

        mockMvc.perform(get("/user/" + domainUser1.id())
                        .header("Authorization", "Bearer " + HardcodedTokenFilter.TEST_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(domainUser1.id()))
                .andExpect(jsonPath("$[0].name").value("Nicholas Runolfsdottir V"))
                .andExpect(jsonPath("$[0].username").value("Maxime_Nienow"))
                .andExpect(jsonPath("$[0].email").value("Sherwood@rosamond.me"))
                .andExpect(jsonPath("$[0].phone").value("586.493.6943 x140"))

                .andExpect(jsonPath("$[1].id").value(domainUser2.id()))
                .andExpect(jsonPath("$[1].name").value("Glenna Reichert"))
                .andExpect(jsonPath("$[1].username").value("Delphine"))
                .andExpect(jsonPath("$[1].email").value("Chaim_McDermott@dana.io"))
                .andExpect(jsonPath("$[1].phone").value("(775)976-6794 x41206"))
        ;
    }

    @Test
    void testGetUser_onValidId_shouldReturnUser() throws Exception {
        when(dataCache.getUsers()).thenReturn(List.of(domainUser1));

        mockMvc.perform(get("/user/" + domainUser1.id())
                        .header("Authorization", "Bearer " + HardcodedTokenFilter.TEST_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(domainUser1.id()))
                .andExpect(jsonPath("$.name").value("Nicholas Runolfsdottir V"))
                .andExpect(jsonPath("$.username").value("Maxime_Nienow"))
                .andExpect(jsonPath("$.email").value("Sherwood@rosamond.me"))
                .andExpect(jsonPath("$.address.street").value("Ellsworth Summit"))
                .andExpect(jsonPath("$.address.suite").value("Suite 729"))
                .andExpect(jsonPath("$.address.city").value("Aliyaview"))
                .andExpect(jsonPath("$.address.zipcode").value("45169"))
                .andExpect(jsonPath("$.address.geo.lat").value("0"))
                .andExpect(jsonPath("$.address.geo.lng").value("0"))
                .andExpect(jsonPath("$.phone").value("586.493.6943 x140"))
                .andExpect(jsonPath("$.website").value("jacynthe.com"))
                .andExpect(jsonPath("$.company.name").value("Abernathy Group"))
                .andExpect(jsonPath("$.company.catchPhrase").value("Implemented secondary concept"))
                .andExpect(jsonPath("$.company.bs").value("e-enable extensible e-tailers"))
        ;
    }

    @Test
    void getUser() {

    }

    private static class Consts {

        private static final String validRoster = """
                [
                {
                    "id": 8,
                        "name": "Nicholas Runolfsdottir V",
                        "username": "Maxime_Nienow",
                        "email": "Sherwood@rosamond.me",
                        "phone": "586.493.6943 x140"
                },
                {
                    "id": 9,
                        "name": "Glenna Reichert",
                        "username": "Delphine",
                        "email": "Chaim_McDermott@dana.io",
                        "phone": "(775)976-6794 x41206"
                },
                ]
                """;


        private final String validUser = """
                {
                    "id": 8,
                    "name": "Nicholas Runolfsdottir V",
                    "username": "Maxime_Nienow",
                    "email": "Sherwood@rosamond.me",
                    "address": {
                        "street": "Ellsworth Summit",
                        "suite": "Suite 729",
                        "city": "Aliyaview",
                        "zipcode": "45169"
                    },
                    "phone": "586.493.6943 x140",
                    "website": "jacynthe.com",
                    "company": {
                        "name": "Abernathy Group",
                        "catchPhrase": "Implemented secondary concept",
                        "bs": "e-enable extensible e-tailers"
                    }
                }
                """;
    }

}