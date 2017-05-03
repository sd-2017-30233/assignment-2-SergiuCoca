package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Sergiu Coca on 5/3/2017.
 */
public class Csv implements Report {
    private PrintWriter pw ;
    private StringBuilder sb = new StringBuilder();
    @Override
    public void generateReport(String title,String quantity) {
        String filepath = "E:\\Facultate\\An3sem2\\PS\\Workspace\\Assignment2\\src\\Books.csv";
        try {
            pw = new PrintWriter(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       // sb.append("Books out of stock");
       // sb.append('\n');

        sb.append("Title:");
        sb.append(',');
        sb.append(title);
        sb.append('\n');

        sb.append("Quantity left:");
        sb.append(',');
        sb.append(quantity);
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
    }
}
