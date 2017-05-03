package Model;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

/**
 * Created by Sergiu Coca on 5/3/2017.
 */
public class ReportFactory {

        public Report getReport(String reportType) throws FileNotFoundException, DocumentException {
            if(reportType == null) return null;
            if(reportType.equalsIgnoreCase("PDF")) return new Pdf();
            else if(reportType.equalsIgnoreCase("CSV")) return new Csv();
            return null;
        }
}
