package NewSurveyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JPanel loginPanel;
    private User user;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = getAuthenticatedUser(email, password);
                if (user != null) {
                    dispose();
                    new Dashboard(user);



                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or Password Invalid",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm(null);
                if (registrationForm.getUser() != null) {
                    user = registrationForm.getUser();
                    dispose();
                    new Dashboard(user);
                }
            }
        });

        setVisible(true);
    }

    private User getAuthenticatedUser(String email, String password) {
        // The code for authentication here
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/users";
        final String USERNAME = "root";
        final String PASSWORD = "1234";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            // Display a user-friendly error message
            JOptionPane.showMessageDialog(LoginForm.this,
                    "An error occurred while trying to authenticate. Please try again later.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return user;
    }

    private void openSurveyDashboard(User user) {
        SwingUtilities.invokeLater(() -> {
            Dashboard Dashboard = new Dashboard(user);
            Dashboard.setVisible(true);
        });
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
