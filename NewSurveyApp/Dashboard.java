package NewSurveyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Dashboard extends JDialog {
    private User user;
    private ArrayList<String> surveyQuestions;
    private ArrayList<ArrayList<String>> surveyOptions;
    private ArrayList<JRadioButton> optionButtons;
    private JButton submitButton;
    private JButton emailButton;
    private Connection connection;
    private String userName;

    public Dashboard(User user) {
        this.user = user;
        surveyQuestions = new ArrayList<>();
        surveyOptions = new ArrayList<>();
        optionButtons = new ArrayList<>();

        setSize(new Dimension(900, 800));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        // Create a connection to the database
        final String DB_URL = "jdbc:mysql://localhost/users";
        final String USERNAME = "root";
        final String PASSWORD = "1234";

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error connecting to the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            setVisible(true);
        }

        // Fetch the user's name from the database
        if (user != null) {
            User dbUser = getUserFromDatabase(user.email, user.password);
            if (dbUser != null) {
                userName = dbUser.name;
            } else {
                JOptionPane.showMessageDialog(this,
                        "User not found in the database.",
                        "User Not Found",
                        JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "User is null.",
                    "User Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        }

        // Add the "Welcome to SYNC Inter's Dashboard" message at the center-top
        JLabel welcomeLabel = new JLabel("Welcome to SYNC Inters Survey Dashboard");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Segue Print", Font.BOLD, 25));
        welcomeLabel.setForeground(Color.WHITE);
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.BLACK);
        welcomePanel.add(welcomeLabel);
        add(welcomePanel, BorderLayout.NORTH);

        // Add other survey components here
        JPanel surveyPanel = new JPanel();
        surveyPanel.setLayout(new BoxLayout(surveyPanel, BoxLayout.Y_AXIS));
        int verticalSpace = 10;

        // Add survey questions and options
        addSurveyQuestion("What is your favorite color?", "Red", "Blue", "Green");
        addVerticalSpace(surveyPanel, verticalSpace);
        addSurveyQuestion("How often do you exercise?", "Never", "Sometimes", "Regularly");
        addVerticalSpace(surveyPanel, verticalSpace);
        addSurveyQuestion("Which programming language do you prefer?", "Java", "Python", "C++");
        addVerticalSpace(surveyPanel, verticalSpace);
        addVerticalSpace(surveyPanel, verticalSpace);

        // Add the survey questions and options to the surveyPanel
        for (int i = 0; i < surveyQuestions.size(); i++) {
            JLabel questionLabel = new JLabel(surveyQuestions.get(i));
            questionLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            questionLabel.setFont(new Font("Segue Print", Font.BOLD, 25));
            questionLabel.setForeground(new Color(18,56,86));
            surveyPanel.add(questionLabel);

            ButtonGroup buttonGroup = new ButtonGroup();
            ArrayList<String> options = surveyOptions.get(i);
            for (String option : options) {
                JRadioButton optionButton = new JRadioButton(option);
                buttonGroup.add(optionButton);
                optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                surveyPanel.add(optionButton);
                optionButtons.add(optionButton);
            }
        }

        // Add the submit button
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if all survey questions are answered
                if (allQuestionsAnswered()) {
                    saveSurveyResponse(); // Save survey response to the database
                    showSuccessMessage(); // Show the "Successfully Submitted" message
                } else {
                    JOptionPane.showMessageDialog(Dashboard.this,
                            "Please answer all the survey questions.",
                            "Incomplete Survey",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        surveyPanel.add(submitButton);

        // Add the Email Send button
        emailButton = new JButton("Email Send");
        emailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailButton.setFont(new Font("Segue Print", Font.BOLD, 15)); // Set font size and style
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmail(); // Send the email with the survey response
            }
        });
        surveyPanel.add(emailButton);

        add(surveyPanel, BorderLayout.CENTER);

        // Display the username in the bottom left corner
        JLabel nameLabel = new JLabel("Logged in as: " + userName);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
        nameLabel.setFont(new Font("Segue Print", Font.PLAIN, 20));
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.BLACK);
        pack();

        setVisible(true);
    }

    private void addVerticalSpace(JPanel panel, int height) {
        panel.add(Box.createRigidArea(new Dimension(750, height)));
    }

    // To add survey question and options
    private void addSurveyQuestion(String question, String... options) {
        surveyQuestions.add(question);
        ArrayList<String> optionList = new ArrayList<>();
        optionList.addAll(Arrays.asList(options));
        surveyOptions.add(optionList);
    }
    //To get survey Question
    private String getSelectedOptionForQuestion(int questionIndex) {
        for (int i = questionIndex * 3; i < (questionIndex + 1) * 3; i++) {
            if (optionButtons.get(i).isSelected()) {
                return optionButtons.get(i).getText();
            }
        }
        return "No response";
    }

    // Fetch user data from the database based on login credentials
    private User getUserFromDatabase(String email, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/users";
        final String USERNAME = "root";
        final String PASSWORD = "1234";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // To save the survey response in the database
    private void saveSurveyResponse() {

        final String SAVE_RESPONSE_QUERY = "INSERT INTO survey_response (user_id, question, response) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RESPONSE_QUERY);
            for (int i = 0; i < surveyQuestions.size(); i++) {
                String question = surveyQuestions.get(i);
                String response = getSelectedOptionForQuestion(i);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, question);
                preparedStatement.setString(3, response);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred while saving the survey response.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
         dispose();
    }

    private void sendEmail() {
        // Get the user's email from the survey response
        String userEmail = "nigamsingh888@gmail.com";

        // Create a new Email instance
        Email emailDialog = new Email();
        emailDialog.setRecipient(userEmail);

        // Show the Email dialog
        emailDialog.setVisible(true);
    }

    // to check if all survey questions are answered
    private boolean allQuestionsAnswered() {
        for (int i = 0; i < surveyQuestions.size(); i++) {
            if (getSelectedOptionForQuestion(i).equals("No response")) {
                return false;
            }
        }
        return true;
    }

    // show the "Successfully Submitted" message
    private void showSuccessMessage() {
        // Hide the survey panel and show the success message panel
        getContentPane().getComponent(1).setVisible(false);
        getContentPane().getComponent(2).setVisible(false);
    }

    public static void main(String[] args) {
        // For testing purposes, you can create a test user here
        User testUser = new User();

        // Create the Dashboard for the test user
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard(testUser);
            dashboard.setVisible(true);
        });
    }
}
