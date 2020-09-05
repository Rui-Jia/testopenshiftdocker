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
    @GetMapping(value = "/login")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(us.getAllUsers());
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
    public ResponseEntity<String> updateUserPassword(@PathVariable long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        long id_returned = us.updateUserPassword(id, oldPassword, newPassword);
        URI uri = URI.create("/" + id_returned);
        return ResponseEntity.created(uri).body(newPassword);
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