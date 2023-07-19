package NewSurveyApp;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.util.Properties;


public class Email extends javax.swing.JFrame {

    private javax.swing.JButton Attachment;
    private javax.swing.JPanel Body;
    private javax.swing.JTextField DirectoryPath;
    private javax.swing.JTextArea MessageArea;
    private javax.swing.JButton Send;
    private javax.swing.JTextField SendTo;
    private javax.swing.JTextField Subject;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;

    public Email() {
        initComponents();
    }
    JFileChooser fileChooser = new JFileChooser();


    @SuppressWarnings("unchecked")
    private void initComponents() {

        Body = new javax.swing.JPanel();
        SendTo = new javax.swing.JTextField();
        Subject = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MessageArea = new javax.swing.JTextArea();
        DirectoryPath = new javax.swing.JTextField();
        Attachment = new javax.swing.JButton();
        Send = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Body.setBackground(new java.awt.Color(73, 197, 182));

        jLabel1.setFont(new java.awt.Font("Segue", 1, 15));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Send To:");

        jLabel2.setFont(new java.awt.Font("Segue",1 , 16));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Subject:");

        MessageArea.setColumns(20);
        MessageArea.setRows(5);
        MessageArea.setOpaque(false);
        jScrollPane1.setViewportView(MessageArea);

        Attachment.setBackground(new java.awt.Color(0, 0, 0));
        Attachment.setFont(new java.awt.Font("Segoe UI", 1, 14));
        Attachment.setForeground(new java.awt.Color(255, 255, 255));
        Attachment.setText("Attachment");
        Attachment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttachmentActionPerformed(evt);
            }
        });

        Send.setBackground(new java.awt.Color(0, 0, 0));
        Send.setFont(new java.awt.Font("MS UI Gothic", 1, 14));
        Send.setForeground(new java.awt.Color(255, 255, 255));
        Send.setText("Send");
        Send.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        // Add action listener to the "Cancel" button
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });


        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("MS UI Gothic", 1, 14));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancel");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segue", 3, 24));
        jLabel3.setText("Send Mail");

        javax.swing.GroupLayout BodyLayout = new javax.swing.GroupLayout(Body);
        Body.setLayout(BodyLayout);
        BodyLayout.setHorizontalGroup(
                BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BodyLayout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(Send)
                                .addGap(40, 40, 40)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BodyLayout.createSequentialGroup()
                                .addContainerGap(112, Short.MAX_VALUE)
                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BodyLayout.createSequentialGroup()
                                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(BodyLayout.createSequentialGroup()
                                                                .addComponent(Attachment, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(DirectoryPath))
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, BodyLayout.createSequentialGroup()
                                                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Subject)
                                                                        .addComponent(SendTo))))
                                                .addGap(113, 113, 113))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BodyLayout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(180, 180, 180))))
        );
        BodyLayout.setVerticalGroup(
                BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BodyLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(SendTo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Attachment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DirectoryPath))
                                .addGap(19, 19, 19)
                                .addGroup(BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(Body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void AttachmentActionPerformed(java.awt.event.ActionEvent evt) {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            DirectoryPath.setText(selectedFile.getAbsolutePath());
        }
    }

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {
        Boolean sendTo = !SendTo.getText().equals("");
        Boolean subject = !Subject.getText().equals("");
        Boolean messageArea = !MessageArea.getText().equals("");

        if (sendTo && subject && messageArea) {
            SendToEmail();
        } else {
            System.out.println("Complete the required fields (SendTo, Subject, and MessageArea).");
        }
    }

    // Updated SendToEmail method with optional file attachment
    public void SendToEmail() {
        final String to = SendTo.getText();
        final String from = "nigamsingh888@gmail.com"; // Change according to your email

        // Embedded account
        String username = "nigamsingh888@gmail.com"; // Change according to your email
        String password = "cjcvrwzwsxzdesxs"; // Change with your App Password created in the google account for less secure app

        // Server and hosting
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(Subject.getText());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message via getText
            messageBodyPart.setText(MessageArea.getText());

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part via getText
            multipart.addBodyPart(messageBodyPart);

            // Check if a file is selected for attachment
            if (!DirectoryPath.getText().isEmpty()) {
                messageBodyPart = new MimeBodyPart();
                File selectedFile = new File(DirectoryPath.getText());
                DataSource source = new FileDataSource(selectedFile);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(selectedFile.getName());
                multipart.addBodyPart(messageBodyPart);
            }

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            showSuccessDialog();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to show a success message dialog and close the email window
    private void showSuccessDialog() {
        JOptionPane.showMessageDialog(this,
                "Email sent successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the Email dialog after sending the email
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        //ClearingValue

        SendTo.setText("");
        Subject.setText("");
        DirectoryPath.setText("");
        MessageArea.setText("");
        dispose();

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Email().setVisible(true);
            }
        });
    }


    public void setRecipient(String userEmail) {
    }
}
