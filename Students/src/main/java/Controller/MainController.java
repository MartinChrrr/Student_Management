package Controller;


import DAO.StudentDAO;
import DAO.UserDAO;
import Models.Student;
import Models.User;
import View.LoginFrame;
import View.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


    //navigation
    //create other controller
    //event button
public class MainController {

    private StudentDAO studentDAO;
    private UserDAO userDAO;
    private StudentController studentController;

    private LoginFrame loginFrame;
    private MainFrame mainFrame;

    private User currentUser;

    public MainController() {
        // Dao Creation
        studentDAO = new StudentDAO();
        userDAO = new UserDAO();
        studentController = new StudentController(studentDAO);

        try {
            studentDAO.loadFromFile();
            userDAO.loadFromFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des données : " + e.getMessage());
        }

        showLoginScreen();
    }

    //Display login
    private void showLoginScreen() {
        loginFrame = new LoginFrame();
        loginFrame.setVisible(true);

        // Gestion du clic sur "Se connecter"
        loginFrame.onLogin(e -> {
            String username = loginFrame.getUsername();
            String password = loginFrame.getPassword();

            //debug
//            System.out.println(username);
//            System.out.println(password);


            User user = userDAO.getUser(username);
            //debug
//            System.out.println(user);
            if (user != null && user.getPassword().equals(password)) {
                currentUser = user;
                loginFrame.dispose();
                showMainScreen();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Identifiants incorrects !");
            }
        });
    }

    //main
    private void showMainScreen() {
        mainFrame = new MainFrame(currentUser.isAdmin());

        // load StudentList
        refreshStudentList();

        // Add Button
        mainFrame.onAddStudent(e -> {
            String name = mainFrame.getStudentNameInput();
            String email = mainFrame.getStudentEmailInput();

            if (name.isBlank() || email.isBlank()) {
                JOptionPane.showMessageDialog(mainFrame, "Veuillez remplir tous les champs !");
                return;
            }

            // Student id generation
            int newId = studentController.getNewId();
            Student newStudent = new Student(newId, name, email);
            studentController.addStudent(newStudent);
            saveStudents();
            refreshStudentList();
        });

        // Delete button
        mainFrame.onDeleteStudent(e -> {
            int selectedId = mainFrame.getSelectedStudentId();
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(mainFrame, "Veuillez sélectionner un étudiant à supprimer.");
                return;
            }

            studentController.removeStudent(selectedId);
            saveStudents();
            refreshStudentList();
        });

        mainFrame.setVisible(true);
    }


    private void refreshStudentList() {
        List<Student> students = studentController.getAllStudents();
        //System.out.println(students.get(0).getEmail());


        mainFrame.updateStudentTable(students);
    }

    //save Student in txt file
    private void saveStudents() {
        try {
            studentDAO.saveToFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, "Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
