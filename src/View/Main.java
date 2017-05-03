package View;

import Controller.Controller;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

/**
 * Created by Sergiu Coca on 5/2/2017.
 */
public class Main {
    public static void main(String [] args) throws FileNotFoundException, DocumentException {
       Login login = new Login();
       RegularUser regularUser=new RegularUser();
       Administrator administrator=new Administrator();

       Controller controller = new Controller(login,regularUser,administrator);
       login.getFrame().setVisible(true);
    }
}
