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

/**
 * Le MainController est le point central de l'application :
 * - il gère la navigation entre les écrans,
 * - il initialise les contrôleurs secondaires,
 * - il réagit aux actions principales de l'utilisateur.
 */
public class MainController {

    private StudentDAO studentDAO;
    private UserDAO userDAO;
    private StudentController studentController;

    private LoginFrame loginFrame;
    private MainFrame mainFrame;

    private User currentUser;

    public MainController() {
        // Initialisation des DAO
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

    /**
     * Affiche la fenêtre de connexion.
     */
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
            System.out.println(user);
            if (user != null && user.getPassword().equals(password)) {
                currentUser = user;
                loginFrame.dispose();
                showMainScreen();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Identifiants incorrects !");
            }
        });
    }

    /**
     * Affiche la fenêtre principale (liste des étudiants, etc.).
     */
    private void showMainScreen() {
        mainFrame = new MainFrame(currentUser.isAdmin());

        // Charger les étudiants existants
        refreshStudentList();

        // Bouton d’ajout d’un étudiant
        mainFrame.onAddStudent(e -> {
            String name = mainFrame.getStudentNameInput();
            String email = mainFrame.getStudentEmailInput();

            if (name.isBlank() || email.isBlank()) {
                JOptionPane.showMessageDialog(mainFrame, "Veuillez remplir tous les champs !");
                return;
            }

            // Génération d’un ID automatique
            int newId = studentController.getNewId();
            Student newStudent = new Student(newId, name, email);
            studentController.addStudent(newStudent);
            saveStudents();
            refreshStudentList();
        });

        // Bouton de suppression d’un étudiant
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

    /**
     * Rafraîchit la liste des étudiants affichée dans la vue.
     */
    private void refreshStudentList() {
        List<Student> students = studentController.getAllStudents();
        //System.out.println(students.get(0).getEmail());


        mainFrame.updateStudentTable(students);
    }

    /**
     * Sauvegarde les données des étudiants dans le fichier.
     */
    private void saveStudents() {
        try {
            studentDAO.saveToFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, "Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
