package View;
import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Connexion Administrateur");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(20, 20, 120, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 20, 120, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Mot de passe:");
        passLabel.setBounds(20, 60, 120, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 60, 120, 25);
        add(passwordField);

        loginButton = new JButton("Se connecter");
        loginButton.setBounds(80, 100, 140, 30);
        add(loginButton);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    public void onLogin(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}

