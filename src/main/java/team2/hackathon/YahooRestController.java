package team2.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yahoo")
@CrossOrigin
public class YahooRestController {

    @Autowired
    public YahooRestClient yahooRestClient;

    @Autowired
    private UserService userService;

    @GetMapping(value="/getSummary")
    public ResponseEntity<String> getYahooSummary(@RequestHeader("Authorization") long id) {
        if(validateUser(id) == false) {
            return ResponseEntity.status(400).body(null);
        }
        String result = yahooRestClient.getSummary();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value="/getMovers")
    public ResponseEntity<String> getYahooMovers(@RequestHeader("Authorization") long id) {
        if(validateUser(id) == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String result = yahooRestClient.getMovers();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value="/getTrendingTickers")
    public ResponseEntity<String> getYahooTrendingTickers(@RequestHeader("Authorization") long id) {
        if(validateUser(id) == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String result = yahooRestClient.getTrendingTickers();
        return ResponseEntity.ok().body(result);
    }

    private boolean validateUser(long id) {
        User user = userService.findUserByUserId(id);
        if(user != null){
            return true;
        } else {
            return false;
        }
    }
}