package team2.hackathon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class YahooApiBuilder {

    @Value("${yahoo.api.host}")
    private String host;

    @Value("${yahoo.api.key}")
    private String key;

    public HttpHeaders getHeaders(HttpHeaders headers) {
        headers.set("x-rapidapi-host", host);
        headers.set("x-rapidapi-key", key);
        return headers;
    }
}