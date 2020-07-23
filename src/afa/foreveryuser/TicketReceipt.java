/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ADI
 */
public class TicketReceipt extends ReceiptMaker {

    @Override
    public int GenerateReceipt(String trans_id) {
        int affectedRows = 0;
        try {
            String qry = "SELECT * FROM ticketbytransid where TransID=" + trans_id + " AND Status = 'NotPrinted'" + ";";
            ResultSet rs = AFAUtils.Select(qry);
            while (rs.next()) {
                String pdf_dir = "src/resources/tickets/" + trans_id + "-" + rs.getString("SeatNo") + ".pdf";
                OutputStream file = new FileOutputStream(new File(pdf_dir));
                Document document = new Document();
                PdfWriter.getInstance(document, file);

                //Inserting Image in PDF
                Image image = Image.getInstance("src/resources/logo.png");//Header Image
                image.scaleAbsolute(540f, 72f);//image width,height 

                PdfPTable billTable = new PdfPTable(2); //one page contains 15 records 
                billTable.setWidthPercentage(100);
                billTable.setWidths(new float[]{1, 1});
                billTable.setSpacingBefore(30.0f);
                billTable.addCell(getBillHeaderCell("Movie Name"));
                billTable.addCell(getBillRowCell(rs.getString("Title")));
                billTable.addCell(getBillHeaderCell("Studio"));
                billTable.addCell(getBillRowCell(rs.getString("Name")));
                billTable.addCell(getBillHeaderCell("Seat"));
                billTable.addCell(getBillRowCell(rs.getString("SeatNo")));
                billTable.addCell(getBillHeaderCell("Date"));
                billTable.addCell(getBillRowCell(rs.getString("MovieDate")));
                billTable.addCell(getBillHeaderCell("Showtime"));
                billTable.addCell(getBillRowCell(rs.getString("Showtimes")));

                PdfPTable describer = new PdfPTable(1);
                describer.setWidthPercentage(100);
                describer.addCell(getdescCell(" "));
                describer.addCell(getdescCell("Thank you for your purchase"));

                document.open();//PDF document opened........	

                document.add(image);
                document.add(billTable);
                document.add(describer);

                document.close();

                file.close();
            }
            String query = "UPDATE tickets SET Status = 'Printed' WHERE TransID = ? AND Status = 'NotPrinted';";
            PreparedStatement preparedStmt = AFAUtils.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, Integer.parseInt(trans_id));
            System.out.println("Pdf created successfully..");
            affectedRows += preparedStmt.executeUpdate();
            return affectedRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        font.setColor(BaseColor.BLACK);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    @Override
    protected PdfPCell getBillRowCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        font.setColor(BaseColor.BLACK);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        //cell.setBorderWidthBottom(1);
        //cell.setBorderWidthTop(1);

        return cell;
    }

}
