package Controller;


import DAO.NotesDAO;
import DAO.StudentDAO;
import DAO.UserDAO;
import Models.Note;
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
    private NotesDAO notesDAO;
    private StudentController studentController;
    private NoteController noteController;
    private LoginFrame loginFrame;
    private MainFrame mainFrame;

    private User currentUser;

    public MainController() {
        // Dao Creation
        studentDAO = new StudentDAO();
        userDAO = new UserDAO();
        notesDAO = new NotesDAO();
        studentController = new StudentController(studentDAO);
        noteController = new NoteController(notesDAO);

        try {
            studentDAO.loadFromFile();
            userDAO.loadFromFile();
            notesDAO.loadFromFile();
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

        mainFrame.onAddNote(e -> {
            int selectedId = mainFrame.getSelectedStudentId();
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(mainFrame, "Veuillez sélectionner un étudiant pour ajouter une note.");
                return;
            }
            String note = mainFrame.getNoteInput();
            //debug
            //System.out.println(note);
            //System.out.println(isDouble(note));

            if (note.isBlank() || !isDouble(note)) {
                JOptionPane.showMessageDialog(mainFrame, "Veuillez mettre une note.");
                return;
            }
            int newId = noteController.getNewId();
            Note newNote = new Note(newId, selectedId, Double.parseDouble(note));
            noteController.addNote(newNote);
            saveNotes();
            refreshStudentList();
        });

        mainFrame.setVisible(true);
    }


    private void refreshStudentList() {
        List<Student> students = studentController.getAllStudents();
        List<Note> notes = noteController.getAllNotes();
        for(int i = 0; i < notes.size(); i++) {
            int studentID =notes.get(i).getStudentID();
            Student student = studentDAO.getById(studentID);
            student.addNote(notes.get(i));
        }
        //debug
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

    private void saveNotes() {
        try {
            notesDAO.saveToFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, "Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    //check if string is int (for note input)
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
