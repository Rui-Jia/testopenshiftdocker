package team2.hackathon;

import org.apache.catalina.UserDatabase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team2.hackathon.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTests {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;


    @Test
    public void getAllUsers_allUsersReturned() {

        User user = new User(1, "username", "password");

        //actual
        List<User> actual = new ArrayList<>();
        actual.add(user);

        when(userService.getAllUsers()).thenReturn(actual);

        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void getUserById_userReturned() {
//input here
    }

    @Test
    public void getUserById_userDoesNotExist() {
//input here
    }

    @Test
    public void insertNewUser_userInserted() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User(1, "username2", "password2");
        when(userService.insertNewUser(any(User.class))).thenReturn(user.getUserId());
        ResponseEntity responseEntity = userController.insertNewUser(user);

        assertEquals(responseEntity.getStatusCodeValue(), 201);
        assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
    }

    @Test
    public void updateUserPassword_successfulChange() {
//    input here
    }

    @Test
    public void deleteUser() {
//   input here
    }

    @Test
    public void deleteAllUsers_usersDeleted() {
//input here
    }
}
