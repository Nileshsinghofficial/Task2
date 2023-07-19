package NewSurveyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class RegistrationForm extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmpassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;

    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Create a  new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500,474));
        setModal(true);
        setLocationRelativeTo(parent);


        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
        setVisible(true);
    }
    private  void registerUser(){
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmpassword.getPassword());

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty()|| password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserTodDatabase(name, email,phone ,password);
        if(user !=null ){
            JOptionPane.showMessageDialog(this,user.name+":Your Registration Successful!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to Register User",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    public User user;
    private User addUserTodDatabase(String name, String email,String phone,
                                    String password){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/users";
        final String USERNAME = "root";
        final String PASSWORD = "1234";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            //Connected to database successfuly...
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO user(name,email,phone,password)" +
                    "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, password);

            //Insert row into table
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.password = password;

            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public User getUser() {
        return user;
    }
    public static void main(String[] args){
        RegistrationForm myForm = new RegistrationForm(null);
        User user = myForm.user;
        if (user != null){
            System.out.println("Successful registration of: "+user.name);

        }
        else {
            System.out.println("Registration Canceled");
        }

    }
}