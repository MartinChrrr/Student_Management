package View;

import Models.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {

    private JTable studentTable;
    private JButton addButton, deleteButton, addNoteButton;

    private JTextField nameField, emailField, noteField;

    private DefaultTableModel tableModel;

    public MainFrame(boolean isAdmin) {
        setTitle("Gestion des Étudiants");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Students table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nom", "Email", "Moyenne"}, 0);
        studentTable = new JTable(tableModel);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Formulaire d’ajout
        JPanel formPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        emailField = new JTextField(10);
        addButton = new JButton("Ajouter");
        deleteButton = new JButton("Supprimer");
        emailField = new JTextField(10);
        noteField = new JTextField(5);
        addNoteButton = new JButton("Ajouter une note");

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(addButton);
        formPanel.add(deleteButton);
        formPanel.add(noteField);
        formPanel.add(addNoteButton);

        if (!isAdmin) {
            addButton.setEnabled(false);
            deleteButton.setEnabled(false);
            addNoteButton.setEnabled(false);
        }

        add(formPanel, BorderLayout.SOUTH);
    }

    public void updateStudentTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getAverage()});
            //tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), 0});

        }
    }

    public String getStudentNameInput() { return nameField.getText(); }
    public String getStudentEmailInput() { return emailField.getText(); }
    public String getNoteInput() { return noteField.getText();}

    public int getSelectedStudentId() {
        int row = studentTable.getSelectedRow();
        if (row == -1) return -1;
        return (int) tableModel.getValueAt(row, 0);
    }

    public void onAddStudent(ActionListener listener) { addButton.addActionListener(listener); }
    public void onDeleteStudent(ActionListener listener) { deleteButton.addActionListener(listener); }
    public void onAddNote(ActionListener listener) { addNoteButton.addActionListener(listener); }
}


