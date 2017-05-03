package View;

/**
 * Created by Sergiu Coca on 4/15/2017.
 */

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

public class Administrator {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JComboBox comboBox = new JComboBox(new DefaultComboBoxModel(new String[] {"  ","Add", "Delete", "Edit" }));
    private JComboBox comboBox_1 = new JComboBox(new DefaultComboBoxModel(new String[] {"  ","Add", "Delete", "Edit" }));
    private JButton btnLogout = new JButton("Logout");
    private JButton btnClear = new JButton("Clear");
    private JButton btnReport = new JButton("Report");
    private JLabel lblmessage = new JLabel("LblMessage");

    public Administrator() {
        initialize();
    }

    public JLabel getLblmessage() {
        return lblmessage;
    }

    public void setLblmessage(JLabel lblmessage) {
        this.lblmessage = lblmessage;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JTextField getTextField_1() {
        return textField_1;
    }

    public void setTextField_1(JTextField textField_1) {
        this.textField_1 = textField_1;
    }

    public JTextField getTextField_2() {
        return textField_2;
    }

    public void setTextField_2(JTextField textField_2) {
        this.textField_2 = textField_2;
    }

    public JTextField getTextField_3() {
        return textField_3;
    }

    public void setTextField_3(JTextField textField_3) {
        this.textField_3 = textField_3;
    }

    public JTextField getTextField_4() {
        return textField_4;
    }

    public void setTextField_4(JTextField textField_4) {
        this.textField_4 = textField_4;
    }

    public JTextField getTextField_5() {
        return textField_5;
    }

    public void setTextField_5(JTextField textField_5) {
        this.textField_5 = textField_5;
    }

    public JTextField getTextField_6() {
        return textField_6;
    }

    public void setTextField_6(JTextField textField_6) {
        this.textField_6 = textField_6;
    }

    public JTextField getTextField_7() {
        return textField_7;
    }

    public void setTextField_7(JTextField textField_7) {
        this.textField_7 = textField_7;
    }

    public JTextField getTextField_8() {
        return textField_8;
    }

    public void setTextField_8(JTextField textField_8) {
        this.textField_8 = textField_8;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public JComboBox getComboBox_1() {
        return comboBox_1;
    }
    public void setComboBox_1(JComboBox comboBox_1) {
        this.comboBox_1 = comboBox_1;
    }

    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 625, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);


        lblmessage.setFont(UIManager.getFont("Label.font"));
        lblmessage.setBounds(196, 270, 285, 14);
        panel.add(lblmessage);
        lblmessage.setVisible(false);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setFont(UIManager.getFont("Label.font"));
        lblTitle.setBounds(10, 56, 46, 14);
        panel.add(lblTitle);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setFont(UIManager.getFont("Label.font"));
        lblAuthor.setBounds(10, 87, 46, 14);
        panel.add(lblAuthor);

        JLabel lblGenre = new JLabel("Genre:");
        lblGenre.setFont(UIManager.getFont("Label.font"));
        lblGenre.setBounds(10, 118, 46, 14);
        panel.add(lblGenre);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(UIManager.getFont("Label.font"));
        lblQuantity.setBounds(10, 151, 68, 14);
        panel.add(lblQuantity);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(UIManager.getFont("Label.font"));
        lblPrice.setBounds(10, 182, 46, 14);
        panel.add(lblPrice);

        textField = new JTextField();
        textField.setFont(UIManager.getFont("TextField.font"));
        textField.setBounds(66, 53, 148, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(UIManager.getFont("TextField.font"));
        textField_1.setBounds(66, 84, 148, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(UIManager.getFont("TextField.font"));
        textField_2.setBounds(66, 115, 86, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setFont(UIManager.getFont("TextField.font"));
        textField_3.setBounds(66, 148, 86, 20);
        panel.add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setFont(UIManager.getFont("TextField.font"));
        textField_4.setBounds(66, 179, 86, 20);
        panel.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblSelectOperation = new JLabel("Select operation for books:");
        lblSelectOperation.setFont(UIManager.getFont("Label.font"));
        lblSelectOperation.setBounds(10, 216, 161, 14);
        panel.add(lblSelectOperation);

        comboBox.setBounds(31, 241, 79, 17);
        panel.add(comboBox);

        JLabel lblBooks = new JLabel("Books");
        lblBooks.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblBooks.setBounds(99, 11, 72, 20);
        panel.add(lblBooks);

        JLabel lblEmployees = new JLabel("Employees");
        lblEmployees.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployees.setBounds(441, 11, 103, 20);
        panel.add(lblEmployees);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(UIManager.getFont("Label.font"));
        lblName.setBounds(352, 56, 46, 14);
        panel.add(lblName);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(UIManager.getFont("Label.font"));
        lblUsername.setBounds(352, 87, 72, 14);
        panel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(UIManager.getFont("Label.font"));
        lblPassword.setBounds(352, 118, 72, 14);
        panel.add(lblPassword);

        JLabel lblRank = new JLabel("Rank:");
        lblRank.setFont(UIManager.getFont("Label.font"));
        lblRank.setBounds(352, 151, 46, 14);
        panel.add(lblRank);

        textField_5 = new JTextField();
        textField_5.setFont(UIManager.getFont("TextField.font"));
        textField_5.setBounds(430, 53, 103, 20);
        panel.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setFont(UIManager.getFont("TextField.font"));
        textField_6.setBounds(430, 84, 103, 20);
        panel.add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setFont(UIManager.getFont("TextField.font"));
        textField_7.setBounds(430, 115, 103, 20);
        panel.add(textField_7);
        textField_7.setColumns(10);

        textField_8 = new JTextField();
        textField_8.setFont(UIManager.getFont("TextField.font"));
        textField_8.setBounds(430, 148, 103, 20);
        panel.add(textField_8);
        textField_8.setColumns(10);

        JLabel lblSelectOperationFor = new JLabel("Select operation for employees:");
        lblSelectOperationFor.setFont(UIManager.getFont("Label.font"));
        lblSelectOperationFor.setBounds(370, 216, 183, 14);
        panel.add(lblSelectOperationFor);

        comboBox_1.setBounds(413, 239, 86, 20);
        panel.add(comboBox_1);

        btnLogout.setFont(UIManager.getFont("Button.font"));
        btnLogout.setBounds(308, 307, 89, 23);
        panel.add(btnLogout);

        btnClear.setFont(UIManager.getFont("Button.font"));
        btnClear.setBounds(184, 307, 89, 23);
        panel.add(btnClear);

        btnReport.setFont(UIManager.getFont("Button.font"));
        btnReport.setBounds(63, 307, 89, 23);
        panel.add(btnReport);
    }
    public boolean empty(){
        if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals(""))
            return true;
        return false;
    }
    public boolean empty1(){
        if(textField_5.getText().equals("") || textField_6.getText().equals("") || textField_7.getText().equals("") || textField_8.getText().equals(""))
            return true;
        return false;
    }

    public void addClearListener(ActionListener cal){
        btnClear.addActionListener(cal);
    }
    public void addLOutListener(ActionListener cal){
        btnLogout.addActionListener(cal);
    }
    public void addBCRUDListener(ActionListener cal){
        comboBox.addActionListener(cal);
    }
    public void addICRUDListener(ActionListener cal){
        comboBox_1.addActionListener(cal);
    }
    public void addReportListener(ActionListener cal){ btnReport.addActionListener(cal);}
}
