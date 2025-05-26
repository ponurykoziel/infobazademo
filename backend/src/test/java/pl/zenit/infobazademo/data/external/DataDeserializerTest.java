package pl.zenit.infobazademo.data.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.zenit.infobazademo.data.domain.Geo;
import pl.zenit.infobazademo.data.domain.User;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class DataDeserializerTest {

    private static pl.zenit.infobazademo.data.domain.User domainUser;

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

        domainUser = new pl.zenit.infobazademo.data.domain.User(
                8,
                "Nicholas Runolfsdottir V",
                "Maxime_Nienow",
                "Sherwood@rosamond.me",
                domainAddress,
                "586.493.6943 x140",
                "jacynthe.com",
                domainCompany
        );

    }

    @Test
    void deserialize_validJsonInput_returnsTranslatedDomainUsers() {
        ObjectMapper objectMapper = new DataDeserializerCfg().dataObjectMapper();
        DataDeserializer deserializer = new DataDeserializer(objectMapper);
        String json = """
                [{
                    "id": 8,
                    "name": "Nicholas Runolfsdottir V",
                    "username": "Maxime_Nienow",
                    "email": "Sherwood@rosamond.me",
                    "address": {
                      "street": "Ellsworth Summit",
                      "suite": "Suite 729",
                      "city": "Aliyaview",
                      "zipcode": "45169",
                      "geo": {
                        "lat": "-14.3990",
                        "lng": "-120.7677"
                      }
                    },
                    "phone": "586.493.6943 x140",
                    "website": "jacynthe.com",
                    "company": {
                      "name": "Abernathy Group",
                      "catchPhrase": "Implemented secondary concept",
                      "bs": "e-enable extensible e-tailers"
                    }
                  }
          ]""";

        List<User> result = deserializer.deserialize(json);

        assertTrue(result.size() == 1);
        assertThat(result.get(0))
                .usingRecursiveComparison()
                .ignoringFields("address", "company")
                .isEqualTo(domainUser);
    }

    @Test
    void deserialize_invalidJsonInput_throwsRuntimeException() {
        ObjectMapper objectMapper = new DataDeserializerCfg().dataObjectMapper();
        DataDeserializer deserializer = new DataDeserializer(objectMapper);
        String invalidJson = "{invalid_json}";

        assertThrows(RuntimeException.class, () -> deserializer.deserialize(invalidJson));
    }

}