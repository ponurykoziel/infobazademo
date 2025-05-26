package pl.zenit.infobazademo.data.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataDeserializer {

    private final ObjectMapper dataObjectMapper;

    public DataDeserializer(ObjectMapper dataObjectMapper) {
        this.dataObjectMapper = dataObjectMapper;
    }

    public List<pl.zenit.infobazademo.data.domain.User> deserialize(String input) {
        try {
            var external = dataObjectMapper.readValue(input, new TypeReference<List<pl.zenit.infobazademo.data.external.User>>() {});
            var local = new DomainAdapter().translate(external);
            return local;
        }
        catch (Exception suppressed) {
            throw new RuntimeException(suppressed);
        }
    }
}
