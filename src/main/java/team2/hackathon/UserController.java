package team2.hackathon;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService us;

    //Get Methods
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(us.getAllUsers());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable long id) {
        User user = us.findUserByUserId(id);

        if(user != null){
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create new user
    @PostMapping(value = "/user", consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity insertNewUser(@RequestBody User u) {
        long id = us.insertNewUser(u);
        URI uri = URI.create("/" + id);
        return ResponseEntity.created(uri).body(u);
    }

    //Update user password
    @PutMapping(value = "/user/{id}", consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Long> updateUserPassword(@PathVariable long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        long id_returned = us.updateUserPassword(id, oldPassword, newPassword);
        if(id_returned == id) {
            return ResponseEntity.ok().body(id_returned);
        } else if(id_returned == -1){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().build(); //should never reach this
        }
    }

    //Delete users
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        if (us.findUserByUserId(id) != null) {
            us.deleteUserByUserId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity<String> deleteAllUsers() {
        us.deleteAllUsers();
        return ResponseEntity.ok().build();
    }
}