package team2.hackathon;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class YahooApiBuilder {

    public static String host = "apidojo-yahoo-finance-v1.p.rapidapi.com";
    public static String key = "eeb564b201msh85ad463bfa8700bp15c146jsnb0a5c9b43962";

    public static HttpHeaders getHeaders(HttpHeaders headers) {
        headers.set("x-rapidapi-host", host);
        headers.set("x-rapidapi-key", key);
        return headers;
    }
}