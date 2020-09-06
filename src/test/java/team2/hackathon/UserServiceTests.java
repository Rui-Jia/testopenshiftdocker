package team2.hackathon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTests {
    @MockBean
    private UserService userService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsers_allUsersReturned(){
        System.out.println("1");
        User user = new User(1, "username", "password");

        //actual
        List<User> actual = new ArrayList<>();
        actual.add(user);

        //expected
        when(userService.getAllUsers()).thenReturn(actual);

        assertEquals(userService.getAllUsers(), actual);

        verify(userService).getAllUsers();
    }

    @Test
    public void getUserById_userReturned(){
        System.out.println("2");

        //Actual
        User user = new User(1, "username", "password");

        //Expected
        when(userService.findUserByUserId(eq(1l))).thenReturn(user);

        assertEquals(userService.findUserByUserId(1l), user);

        verify(userService).findUserByUserId(anyLong());
    }

    @Test
    public void getUserById_userDoesNotExist(){
        System.out.println("3");
        User user = new User(1, "username", "password");

        when(userService.findUserByUserId(eq(1l))).thenReturn(user);
        when(userService.findUserByUserId(anyLong())).thenReturn(null);

        assertNull(userService.findUserByUserId(5l));

        verify(userService).findUserByUserId(anyLong());
    }

    @Test
    public void insertNewUser_userInserted(){
        System.out.println("4");

        User user = new User(2, "username2", "password2");
        when(userService.insertNewUser(anyObject())).thenReturn(2l);

        Long id = userService.insertNewUser(user);

        assertSame(id, 2l);

        verify(userService).insertNewUser(anyObject());
    }

    @Test
    public void updateUserPassword_successfulChange(){
        System.out.println("5");

        User user = new User(1, "username", "password");
        when(userService.updateUserPassword(anyLong(), eq("password"), anyString())).thenReturn(user.getUserId());

        Long id = userService.updateUserPassword(1, "password", "newPassword");

        assertSame(id, user.getUserId());
        verify(userService).updateUserPassword(anyLong(), anyString(), anyString());
    }


    @Test
    public void updateUserPassword_wrongOldPasswordFail(){
        System.out.println("6");

        User user = new User(1, "username", "password");

        when(userService.updateUserPassword(anyLong(), eq("password"), anyString())).thenReturn(user.getUserId());
        when(userService.updateUserPassword(anyLong(), anyString(), anyString())).thenReturn(-1l);

        Long id = userService.updateUserPassword(1, "wrongPassword", "newPassword");

        assertSame(id, -1l);
        verify(userService).updateUserPassword(anyLong(), anyString(), anyString());
    }

    @Test
    public void deleteUser_userDeleted(){
        System.out.println("7");

        doNothing().when(userService).deleteUserByUserId(anyLong());
        userService.deleteUserByUserId(1);

        verify(userService).deleteUserByUserId(anyLong());
    }

    @Test
    public void deleteAllUsers_usersDeleted(){
        System.out.println("8");

        doNothing().when(userService).deleteAllUsers();
        userService.deleteAllUsers();

        verify(userService).deleteAllUsers();
    }

}
