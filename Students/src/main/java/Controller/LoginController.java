package Controller;

import DAO.UserDAO;
import Models.User;

import java.util.Optional;

public class LoginController {
    private UserDAO userDAO;

    public LoginController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    //check password from username
    public User login(String username, String password) {
        User user = userDAO.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
           
            return user;
        }
        return null;
    }
}
