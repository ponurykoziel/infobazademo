package pl.zenit.infobazademo.data.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataFetcher {

    private final RestTemplate dataRestTemplate;
    private final String endpoint;

    public DataFetcher(RestTemplate dataRestTemplate,
                       @Value("${jsonplaceholder.api.endpoint}") String endpoint) {
        this.dataRestTemplate = dataRestTemplate;
        this.endpoint = endpoint;
    }


    public String fetchData() {
        var resp = dataRestTemplate.exchange(endpoint, HttpMethod.GET, null, String.class);
        return resp.getBody();
    }

}
