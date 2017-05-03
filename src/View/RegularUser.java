package View;

/**
 * Created by Sergiu Coca on 4/15/2017.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;

public class RegularUser {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JScrollPane scrollPane= new JScrollPane();
    private JButton btnSearch = new JButton("Search");
    private JButton btnSell = new JButton("Sell");
    private JButton btnList = new JButton("List");
    private JButton btnLogout = new JButton("Logout");
    private JComboBox comboBox = new JComboBox(
            new DefaultComboBoxModel(new String[] {" ","Title", "Author", "Genre" }));


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

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public RegularUser() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 830, 375);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setFont(UIManager.getFont("TextField.font"));
        textField.setBounds(125, 142, 86, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(UIManager.getFont("TextField.font"));
        textField_1.setBounds(10, 62, 190, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(UIManager.getFont("TextField.font"));
        textField_2.setBounds(83, 230, 301, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);


        scrollPane.setBounds(414, 62, 390, 263);
        panel.add(scrollPane);

        String[] columnNames={"","","","","",""};
        Object[][] data=new Object[10][6];
        JTable table = new JTable(data,columnNames);
        scrollPane.setViewportView(table);

        JLabel lblSearchBookBy = new JLabel("Search book by:");
        lblSearchBookBy.setFont(UIManager.getFont("Label.font"));
        lblSearchBookBy.setBounds(10, 28, 94, 14);
        panel.add(lblSearchBookBy);

        JLabel lblSellBookNumber = new JLabel("Sell book name:");
        lblSellBookNumber.setFont(UIManager.getFont("Label.font"));
        lblSellBookNumber.setBounds(10, 145, 120, 14);
        panel.add(lblSellBookNumber);

        JLabel lblMessage = new JLabel("Message:");
        lblMessage.setFont(UIManager.getFont("Label.font"));
        lblMessage.setBounds(10, 233, 74, 14);
        panel.add(lblMessage);

        comboBox.setFont(UIManager.getFont("ComboBox.font"));
        comboBox.setBounds(109, 27, 80, 17);
        panel.add(comboBox);

        btnSearch.setFont(UIManager.getFont("Button.font"));
        btnSearch.setBounds(228, 61, 86, 23);
        panel.add(btnSearch);

        btnSell.setFont(UIManager.getFont("Button.font"));
        btnSell.setBounds(228, 141, 86, 23);
        panel.add(btnSell);

        btnList.setFont(UIManager.getFont("Button.font"));
        btnList.setBounds(568, 24, 86, 23);
        panel.add(btnList);

        btnLogout.setFont(UIManager.getFont("Button.font"));
        btnLogout.setBounds(122, 285, 89, 23);
        panel.add(btnLogout);
    }

    public void addSearchListener(ActionListener cal){
        btnSearch.addActionListener(cal);
    }
    public void addListListener(ActionListener cal){
        btnList.addActionListener(cal);
    }
    public void addOutListener(ActionListener cal){
        btnLogout.addActionListener(cal);
    }
    public void addSellListener(ActionListener cal){
        btnSell.addActionListener(cal);
    }
}

