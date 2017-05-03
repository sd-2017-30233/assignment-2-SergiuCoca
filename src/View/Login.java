package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sergiu Coca on 4/15/2017.
 */
public class Login {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField textField_1;
    private JButton btnExit = new JButton("Exit");
    private JButton btnLogin = new JButton("Login");
    private JLabel LblLogin = new JLabel("Invalid login, please try again");

    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public JTextField getTextField() {
        return textField;
    }
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
    public JPasswordField getTextField_1() {
        return textField_1;
    }
    public void setTextField_1(JPasswordField textField_1) {
        this.textField_1 = textField_1;
    }
    public JLabel getLblLogin() {
        return LblLogin;
    }
    public void setLblLogin(JLabel lblLogin) {
        LblLogin = lblLogin;
    }


    public Login() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(UIManager.getFont("Label.font"));
        lblUsername.setBounds(110, 77, 73, 14);
        panel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(UIManager.getFont("Label.font"));
        lblPassword.setBounds(110, 108, 73, 14);
        panel.add(lblPassword);

        LblLogin.setFont(UIManager.getFont("Label.font"));
        LblLogin.setForeground(Color.RED);
        LblLogin.setBounds(141, 38, 187, 14);
        panel.add(LblLogin);
        LblLogin.setVisible(false);

        btnExit.setFont(UIManager.getFont("Button.font"));
        btnExit.setBounds(230, 163, 89, 23);
        panel.add(btnExit);

        textField = new JTextField();
        textField.setFont(UIManager.getFont("TextField.font"));
        textField.setBounds(193, 74, 86, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JPasswordField();
        textField_1.setFont(UIManager.getFont("TextField.font"));
        textField_1.setBounds(193, 105, 86, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);

        btnLogin.setFont(UIManager.getFont("Button.font"));
        btnLogin.setBounds(110, 163, 89, 23);
        panel.add(btnLogin);
    }

    public void addExitListener(ActionListener cal){
        btnExit.addActionListener(cal);
    }
    public void addLoginListener(ActionListener cal){
        btnLogin.addActionListener(cal);
    }
}