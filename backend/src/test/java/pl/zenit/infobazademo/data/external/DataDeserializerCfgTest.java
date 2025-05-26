package pl.zenit.infobazademo.data.external;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DataDeserializerCfgTest {

    @Test
    void testCfg_providedMapper_shouldBeConfigured() {
        DataDeserializerCfg config = new DataDeserializerCfg();
        ObjectMapper objectMapper = config.dataObjectMapper();
        assertFalse(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        assertFalse(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES));
    }

}