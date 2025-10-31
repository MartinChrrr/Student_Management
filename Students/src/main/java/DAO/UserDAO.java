package DAO;

import Models.User;

import java.io.IOException;
import java.util.*;

public class UserDAO {
    private static final String FILE_PATH = "Students/src/main/ressources/data/users.txt";
    private Map<String, User> users = new HashMap<>();

    //load users from file
    public void loadFromFile() throws IOException {


        List<String> lines = DataStorage.readFromFile(FILE_PATH);
        for(String line: lines) {
            String[] parts = line.split(";");
            if (parts.length >= 3) {
                String username = parts[0];
                String password = parts[1];
                boolean isAdmin = Boolean.parseBoolean(parts[2]);
                //debug
                //System.out.println(isAdmin);
                users.put(username, new User(username, password, isAdmin));
            }
        }
    }


//save all user on file
    public void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (User user : users.values()) {
            lines.add(user.getUsername() + ";" + user.getPassword() + ";" + user.isAdmin());
        }
        DataStorage.writeToFile(FILE_PATH, lines);
    }


    public User getUser(String username) {
        //System.out.println();
        return users.get(username);
    }


    //add new user, if already exist update
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }


    public void removeUser(String username) {
        users.remove(username);
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }


}
