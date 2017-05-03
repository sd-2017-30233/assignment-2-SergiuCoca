package Controller;

import Model.Pdf;
import Model.Report;
import Model.ReportFactory;
import View.Administrator;
import View.Login;
import View.RegularUser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.itextpdf.text.DocumentException;
import org.w3c.dom.*;

/**
 * Created by Sergiu Coca on 5/2/2017.
 */
public class Controller {
    private ReportFactory reportFactory=new ReportFactory();
    private Report report1=reportFactory.getReport("PDF");
    private Report report2=reportFactory.getReport("CSV");
    private Login login;
    private Administrator administrator;
    private RegularUser regularUser;
    private int id=3;
    private int id1=3;
    private String filepath = "E:\\Facultate\\An3sem2\\PS\\Workspace\\Assignment2\\src\\books.xml";
    private String filepath1="E:\\Facultate\\An3sem2\\PS\\Workspace\\Assignment2\\src\\users.xml";

    public Controller(Login login, RegularUser regularUser, Administrator administrator) throws FileNotFoundException, DocumentException {
        this.login = login;
        this.login.addExitListener(new Exit());
        this.login.addLoginListener(new Log());

        this.regularUser = regularUser;
        this.regularUser.addListListener(new RegList());
        this.regularUser.addOutListener(new RegOut());
        this.regularUser.addSearchListener(new RegSearch());
        this.regularUser.addSellListener(new RegSell());

        this.administrator = administrator;
        this.administrator.addBCRUDListener(new BCRUD());
        this.administrator.addICRUDListener(new ICRUD());
        this.administrator.addClearListener(new Clear());
        this.administrator.addLOutListener(new LOut());
        this.administrator.addReportListener(new GenReport());
    }

    //----------------------------------------------------------------------Login---------------------------------------------------------------------------
    public class Exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public class Log implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String rank = "";
            try {

                File fXmlFile = new File("E:\\Facultate\\An3sem2\\PS\\Workspace\\Assignment2\\src\\users.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("user");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if ((login.getTextField().getText().equals(eElement.getElementsByTagName("username").item(0).getTextContent()))
                                && (login.getTextField_1().getText().equals(eElement.getElementsByTagName("password").item(0).getTextContent())))
                            rank = eElement.getElementsByTagName("rank").item(0).getTextContent();
                    }
                }

                if (rank.equals("0")) {
                    regularUser.getFrame().setVisible(true);
                    login.getFrame().setVisible(false);
                } else if (rank.equals("1")) {
                    administrator.getFrame().setVisible(true);
                    login.getFrame().setVisible(false);
                } else login.getLblLogin().setVisible(true);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
    //----------------------------------------------------------------------Login---------------------------------------------------------------------------


    //----------------------------------------------------------------------ReguarUser---------------------------------------------------------------------------
    public class RegList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String[] columnNames = {"Id", "Title", "Author", "Genre", "Quantity", "Price"};
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(filepath);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("book");
                Object[][] data = new Object[nList.getLength()][6];

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        data[temp][0] = eElement.getAttribute("id");
                        data[temp][1] = eElement.getElementsByTagName("title").item(0).getTextContent();
                        data[temp][2] = eElement.getElementsByTagName("author").item(0).getTextContent();
                        data[temp][3] = eElement.getElementsByTagName("genre").item(0).getTextContent();
                        data[temp][4] = eElement.getElementsByTagName("quantity").item(0).getTextContent();
                        data[temp][5] = eElement.getElementsByTagName("price").item(0).getTextContent();
                    }
                }
                JTable table = new JTable(data, columnNames);
                regularUser.getScrollPane().setViewportView(table);
                regularUser.getTextField().setText("");
                regularUser.getTextField_1().setText("");
                regularUser.getTextField_2().setText("");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
        public class RegOut implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.getTextField().setText("");
                login.getTextField_1().setText("");
                login.getLblLogin().setVisible(false);
                regularUser.getFrame().setVisible(false);
                login.getFrame().setVisible(true);
            }
        }

        public class RegSell implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantity;
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(filepath);
                    doc.getDocumentElement().normalize();
                    NodeList list = doc.getElementsByTagName("book");

                    for (int temp = 0; temp < list.getLength(); temp++) {
                        Node nNode = list.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            if (eElement.getElementsByTagName("title").item(0).getTextContent().equals(regularUser.getTextField().getText())) {
                                quantity = Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent());
                                quantity--;
                                if(quantity>0) {
                                    eElement.getElementsByTagName("quantity").item(0).setTextContent(String.valueOf(quantity));
                                    regularUser.getTextField_2().setText("A book was sold!");
                                }
                                else regularUser.getTextField_2().setText("This book is out of stock!");

                            }
                        }
                    }

                    try {
                        doc.getDocumentElement().normalize();
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File(filepath));
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.transform(source, result);
                        System.out.println("XML file updated successfully");
                    } catch (TransformerException te1) {
                        te1.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }

        public class RegSearch implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int i=0;
                    boolean ok=false;
                    String[] columnNames = {"Id", "Title", "Author", "Genre", "Quantity", "Price"};
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(filepath);
                    doc.getDocumentElement().normalize();
                    NodeList list = doc.getElementsByTagName("book");
                    Object[][] data = new Object[list.getLength()][6];

                    switch (regularUser.getComboBox().getSelectedItem().toString()) {
                        case "Title" :
                            for (int temp = 0; temp < list.getLength(); temp++) {
                                Node nNode = list.item(temp);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    if (eElement.getElementsByTagName("title").item(0).getTextContent().equals(regularUser.getTextField_1().getText())) {
                                        ok=true;
                                        regularUser.getTextField_2().setText("This title was found!");
                                        data[i][0] = eElement.getAttribute("id");
                                        data[i][1] = eElement.getElementsByTagName("title").item(0).getTextContent();
                                        data[i][2] = eElement.getElementsByTagName("author").item(0).getTextContent();
                                        data[i][3] = eElement.getElementsByTagName("genre").item(0).getTextContent();
                                        data[i][4] = eElement.getElementsByTagName("quantity").item(0).getTextContent();
                                        data[i][5] = eElement.getElementsByTagName("price").item(0).getTextContent();
                                        i++;
                                    }
                                }
                            }
                            if(!ok) regularUser.getTextField_2().setText("This title was not found!");
                            JTable table = new JTable(data, columnNames);
                            regularUser.getScrollPane().setViewportView(table);
                             break;
                        case "Author":
                            for (int temp = 0; temp < list.getLength(); temp++) {
                                Node nNode = list.item(temp);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    if (eElement.getElementsByTagName("author").item(0).getTextContent().equals(regularUser.getTextField_1().getText())) {
                                        ok=true;
                                        regularUser.getTextField_2().setText("This author was found!");
                                        data[i][0] = eElement.getAttribute("id");
                                        data[i][1] = eElement.getElementsByTagName("title").item(0).getTextContent();
                                        data[i][2] = eElement.getElementsByTagName("author").item(0).getTextContent();
                                        data[i][3] = eElement.getElementsByTagName("genre").item(0).getTextContent();
                                        data[i][4] = eElement.getElementsByTagName("quantity").item(0).getTextContent();
                                        data[i][5] = eElement.getElementsByTagName("price").item(0).getTextContent();
                                        i++;
                                    }
                                }
                            }
                            if(!ok) regularUser.getTextField_2().setText("This author was not found!");
                            JTable table1 = new JTable(data, columnNames);
                            regularUser.getScrollPane().setViewportView(table1);
                            break;
                        case "Genre":
                            for (int temp = 0; temp < list.getLength(); temp++) {
                                Node nNode = list.item(temp);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    if (eElement.getElementsByTagName("genre").item(0).getTextContent().equals(regularUser.getTextField_1().getText())) {
                                        ok=true;
                                        regularUser.getTextField_2().setText("This genre was found!");
                                        data[i][0] = eElement.getAttribute("id");
                                        data[i][1] = eElement.getElementsByTagName("title").item(0).getTextContent();
                                        data[i][2] = eElement.getElementsByTagName("author").item(0).getTextContent();
                                        data[i][3] = eElement.getElementsByTagName("genre").item(0).getTextContent();
                                        data[i][4] = eElement.getElementsByTagName("quantity").item(0).getTextContent();
                                        data[i][5] = eElement.getElementsByTagName("price").item(0).getTextContent();
                                        i++;
                                    }
                                }
                            }
                            if(!ok) regularUser.getTextField_2().setText("This genre was not found!");
                            JTable table2 = new JTable(data, columnNames);
                            regularUser.getScrollPane().setViewportView(table2);
                            break;
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        //----------------------------------------------------------------------ReguarUser---------------------------------------------------------------------------

        //------------------------------------------------------------------Administrator---------------------------------------------------------------------------
        public class BCRUD implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                     DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                     Document document = documentBuilder.parse(filepath);
                     Element root = document.getDocumentElement();
                    NodeList list = document.getElementsByTagName("book");

                switch (administrator.getComboBox().getSelectedItem().toString()) {
                    case "Add":
                        if (!administrator.empty()) {
                            Element newBook = document.createElement("book");
                            id++;
                            String value=id+"";
                            newBook.setAttribute("id", value);

                            Element title = document.createElement("title");
                            title.appendChild(document.createTextNode(administrator.getTextField().getText()));
                            newBook.appendChild(title);

                            Element author = document.createElement("author");
                            author.appendChild(document.createTextNode(administrator.getTextField_1().getText()));
                            newBook.appendChild(author);

                            Element genre = document.createElement("genre");
                            genre.appendChild(document.createTextNode(administrator.getTextField_2().getText()));
                            newBook.appendChild(genre);

                            Element quantity = document.createElement("quantity");
                            quantity.appendChild(document.createTextNode(administrator.getTextField_3().getText()));
                            newBook.appendChild(quantity);

                            Element price = document.createElement("price");
                            price.appendChild(document.createTextNode(administrator.getTextField_4().getText()));
                            newBook.appendChild(price);

                            root.appendChild(newBook);

                            document.getDocumentElement().normalize();
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(document);
                            StreamResult result = new StreamResult(new File(filepath));
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.transform(source, result);
                            System.out.println("XML file updated successfully");

                            administrator.getLblmessage().setText("Book has been added!");
                            administrator.getLblmessage().setVisible(true);
                        } else {
                            administrator.getLblmessage().setText("Fill up all fields!");
                            administrator.getLblmessage().setVisible(true);
                        }
                        break;
                    case "Delete":
                        for (int temp = 0; temp < list.getLength(); temp++) {
                            Node nNode = list.item(temp);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;
                                if (eElement.getElementsByTagName("title").item(0).getTextContent().equals(administrator.getTextField().getText())) {

                                }
                            }
                        }
                        break;
                    case "Edit":
                        for (int temp = 0; temp < list.getLength(); temp++) {
                            Node nNode = list.item(temp);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;
                                if (eElement.getElementsByTagName("title").item(0).getTextContent().equals(administrator.getTextField().getText())) {
                                   String newPrice= administrator.getTextField_4().getText();
                                    eElement.getElementsByTagName("price").item(0).setTextContent(newPrice);
                                    administrator.getLblmessage().setText("The price was changed!");
                                    administrator.getLblmessage().setVisible(true);
                                }
                            }
                        }
                        document.getDocumentElement().normalize();
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(document);
                        StreamResult result = new StreamResult(new File(filepath));
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.transform(source, result);
                        System.out.println("XML file updated successfully");
                        break;
                }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        public class ICRUD implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(filepath1);
                    Element root = document.getDocumentElement();
                    NodeList list = document.getElementsByTagName("user");
                    switch (administrator.getComboBox_1().getSelectedItem().toString()) {
                        case "Add":
                            if (!administrator.empty1()) {
                                Element newUser = document.createElement("user");
                                id1++;
                                String value=id1+"";
                                newUser.setAttribute("id", value);

                                Element name = document.createElement("name");
                                name.appendChild(document.createTextNode(administrator.getTextField_5().getText()));
                                newUser.appendChild(name);

                                Element username = document.createElement("username");
                                username.appendChild(document.createTextNode(administrator.getTextField_6().getText()));
                                newUser.appendChild(username);

                                Element password = document.createElement("password");
                                password.appendChild(document.createTextNode(administrator.getTextField_7().getText()));
                                newUser.appendChild(password);

                                Element rank = document.createElement("rank");
                                rank.appendChild(document.createTextNode(administrator.getTextField_8().getText()));
                                newUser.appendChild(rank);

                                root.appendChild(newUser);

                                document.getDocumentElement().normalize();
                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                DOMSource source = new DOMSource(document);
                                StreamResult result = new StreamResult(new File(filepath1));
                                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                                transformer.transform(source, result);
                                System.out.println("XML file updated successfully");

                                administrator.getLblmessage().setText("User has been added!");
                                administrator.getLblmessage().setVisible(true);
                            } else {
                                administrator.getLblmessage().setText("Fill up all fields!");
                                administrator.getLblmessage().setVisible(true);
                            }
                            break;
                        case "Delete":
                            for (int temp = 0; temp < list.getLength(); temp++) {
                                Node nNode = list.item(temp);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    if (eElement.getElementsByTagName("title").item(0).getTextContent().equals(administrator.getTextField().getText())) {

                                    }
                                }
                            }
                            break;
                        case "Edit":
                            for (int temp = 0; temp < list.getLength(); temp++) {
                                Node nNode = list.item(temp);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(administrator.getTextField_5().getText())) {
                                        String newRank= administrator.getTextField_8().getText();
                                        eElement.getElementsByTagName("rank").item(0).setTextContent(newRank);
                                        administrator.getLblmessage().setText("The rank was changed!");
                                        administrator.getLblmessage().setVisible(true);
                                    }
                                }
                            }
                            document.getDocumentElement().normalize();
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(document);
                            StreamResult result = new StreamResult(new File(filepath1));
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.transform(source, result);
                            System.out.println("XML file updated successfully");
                            break;
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }

            }
        }
        public class LOut implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.getTextField().setText("");
                login.getTextField_1().setText("");
                login.getLblLogin().setVisible(false);
                administrator.getFrame().setVisible(false);
                login.getFrame().setVisible(true);
            }
        }
        public class Clear implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                administrator.getTextField().setText(" ");
                administrator.getTextField_1().setText(" ");
                administrator.getTextField_2().setText(" ");
                administrator.getTextField_3().setText(" ");
                administrator.getTextField_4().setText(" ");
                administrator.getTextField_5().setText(" ");
                administrator.getTextField_6().setText(" ");
                administrator.getTextField_7().setText(" ");
                administrator.getTextField_8().setText(" ");
                administrator.getLblmessage().setText("");
            }
        }
        public class GenReport implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantity;
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(filepath);
                    doc.getDocumentElement().normalize();
                    NodeList list = doc.getElementsByTagName("book");

                    for (int temp = 0; temp < list.getLength(); temp++) {
                        Node nNode = list.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            quantity = Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent());
                                if(quantity<5) {
                                    regularUser.getTextField_2().setText("Almost out of stock, a report was generated!");
                                    report1.generateReport(eElement.getElementsByTagName("title").item(0).getTextContent(),String.valueOf(quantity));
                                    report2.generateReport(eElement.getElementsByTagName("title").item(0).getTextContent(),String.valueOf(quantity));
                                }
                            }
                        }
                        administrator.getLblmessage().setText("A report was generated!");
                        administrator.getLblmessage().setVisible(true);
                        Pdf.document.close();
                }catch (Exception ee) {
                    ee.printStackTrace();
                }
            }

        }
        //------------------------------------------------------------------Administrator---------------------------------------------------------------------
    }