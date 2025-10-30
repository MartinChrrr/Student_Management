package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentFormDialog extends JDialog {

    private JTextField nameField, emailField;
    private JButton confirmButton, cancelButton;

    public StudentFormDialog(JFrame parent, String title) {
        super(parent, title, true);
        setLayout(new GridLayout(3, 2, 10, 10));
        setSize(300, 180);
        setLocationRelativeTo(parent);

        add(new JLabel("Nom:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        confirmButton = new JButton("Valider");
        cancelButton = new JButton("Annuler");
        add(confirmButton);
        add(cancelButton);
    }

    public String getNameInput() { return nameField.getText(); }
    public String getEmailInput() { return emailField.getText(); }

    public void setNameInput(String name) { nameField.setText(name); }
    public void setEmailInput(String email) { emailField.setText(email); }

    public void onConfirm(ActionListener listener) { confirmButton.addActionListener(listener); }
    public void onCancel(ActionListener listener) { cancelButton.addActionListener(listener); }
}

