package Model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Sergiu Coca on 5/3/2017.
 */
public class Pdf implements Report {
     public static Document document = new Document(PageSize.A4, 50, 50, 50, 50);
     private  String filepath = "E:\\Facultate\\An3sem2\\PS\\Workspace\\Assignment2\\src\\Books.pdf";
     private  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filepath));

    public Pdf() throws FileNotFoundException, DocumentException {
    }

    @Override
    public void generateReport(String title,String quantity) {
        Anchor anchorTarget = new Anchor("Books out of stock:");
        anchorTarget.setName("BackToTop");
        document.open();
        Paragraph paragraph1 = new Paragraph();

        paragraph1.setSpacingBefore(50);

        paragraph1.add(anchorTarget);
        try {
            document.add(paragraph1);
            document.add(new Paragraph("\nThe book:"+title+" is almost out of stock," +quantity+" left!\n",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.ITALIC, new CMYKColor(0, 255, 255, 0))));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
       // document.close();
    }
}
