package team2.hackathon;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class YahooRestClient {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = YahooApiBuilder.getHeaders(new HttpHeaders());

    public String getSummary() {
        String uri = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en";

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<String> responseType =
                new ParameterizedTypeReference<String>() {};

        ResponseEntity<String> response =  restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);

        String result = response.getBody();
        return result;
    }

    public String getMovers() {
        String uri = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-movers?region=US&lang=en";

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<String> responseType =
                new ParameterizedTypeReference<String>() {};

        ResponseEntity<String> response =  restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);

        String result = response.getBody();
        return result;
    }

    public String getTrendingTickers() {
        String uri = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-trending-tickers?region=US";

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<String> responseType =
                new ParameterizedTypeReference<String>() {};

        ResponseEntity<String> response =  restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);

        String result = response.getBody();
        return result;
    }
}