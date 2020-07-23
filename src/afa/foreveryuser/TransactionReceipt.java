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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ADI
 */
public class TransactionReceipt extends ReceiptMaker {

    @Override
    public int GenerateReceipt(String trans_id) {
        try {
            String qry = "SELECT * FROM transactions where TransID=" + trans_id + ";";
            ResultSet rs = AFAUtils.Select(qry);
            PdfPTable irdTable = new PdfPTable(2);
            PdfPTable irhTable = new PdfPTable(3);
            irhTable.setWidthPercentage(100);
            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            while (rs.next()) {
                
                irdTable.addCell(getIRDCell("Trans ID"));
                irdTable.addCell(getIRDCell(rs.getString("TransID"))); // pass trans number
                irdTable.addCell(getIRDCell("Trans Date"));
                irdTable.addCell(getIRDCell(rs.getString("TransDateTime")));  // pass trans date
                irdTable.addCell(getIRDCell("Trans PIN")); 
                irdTable.addCell(getIRDCell(rs.getString("PIN"))); // pass invoice pin			

                
                

                irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(getIRHCell("Transaction", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
                PdfPCell invoiceTable = new PdfPCell(irdTable);
                invoiceTable.setBorder(0);
                irhTable.addCell(invoiceTable);

                
                
                accounts.addCell(getAccountsCell("Subtotal"));
                accounts.addCell(getAccountsCellR(rs.getString("TotalPrice")));
                accounts.addCell(getAccountsCell("Discount (0%)"));
                accounts.addCell(getAccountsCellR("0"));
                accounts.addCell(getAccountsCell("Tax(0%)"));
                accounts.addCell(getAccountsCellR("0"));
                accounts.addCell(getAccountsCell("Total"));
                accounts.addCell(getAccountsCellR(rs.getString("TotalPrice")));

            }
            String pdf_dir = "src/resources/trans/" + trans_id + ".pdf";
            OutputStream file = new FileOutputStream(new File(pdf_dir));
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            //Inserting Image in PDF
            Image image = Image.getInstance("src/resources/logo.png");//Header Image
            image.scaleAbsolute(540f, 72f);//image width,height 

            PdfPTable billTable = new PdfPTable(6); //one page contains 15 records 
            billTable.setWidthPercentage(100);
            billTable.setWidths(new float[]{1, 2, 5, 2, 1, 2});
            billTable.setSpacingBefore(30.0f);
            billTable.addCell(getBillHeaderCell("No"));
            billTable.addCell(getBillHeaderCell("Item"));
            billTable.addCell(getBillHeaderCell("Description"));
            billTable.addCell(getBillHeaderCell("Unit Price"));
            billTable.addCell(getBillHeaderCell("Qty"));
            billTable.addCell(getBillHeaderCell("Amount"));

            qry = "SELECT * FROM ticketbytransid where TransID=" + trans_id + ";";
            rs = AFAUtils.Select(qry);
            int x = 1;
            String description;
            while (rs.next()) {
                description = String.format("%s | %s \n", rs.getString("Title"), rs.getString("Name"))
                        + String.format("%s | %s \n", rs.getString("MovieDate"), rs.getString("Showtimes"))
                        + String.format("%s", rs.getString("SeatNo"));
                billTable.addCell(getBillRowCell(Integer.toString(x)));
                billTable.addCell(getBillRowCell("Ticket"));
                billTable.addCell(getBillRowCell(description));
                billTable.addCell(getBillRowCell(rs.getString("Price")));
                billTable.addCell(getBillRowCell("1"));
                billTable.addCell(getBillRowCell(rs.getString("Price")));
                x++;
            }
            
            
            PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell("Disclaimer"));
            validity.addCell(getValidityCell(" * Please print ticket before the showtimes"));
            validity.addCell(getValidityCell(" * Transaction has been made, cant be refund"));
            PdfPCell summaryL = new PdfPCell(validity);
            summaryL.setColspan(3);
            summaryL.setPadding(1.0f);
            billTable.addCell(summaryL);
            
            PdfPCell summaryR = new PdfPCell(accounts);
            summaryR.setColspan(3);
            billTable.addCell(summaryR);


            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescCell(" "));
            describer.addCell(getdescCell("Thank you for your purchase"));

            document.open();//PDF document opened........	

            document.add(image);
            document.add(irhTable);
            document.add(billTable);
            document.add(describer);

            document.close();

            file.close();

            System.out.println("Pdf created successfully..");
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    protected PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    @Override
    protected PdfPCell getBillRowCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    private static PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        /*	font.setColor(BaseColor.GRAY);*/
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    private static PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        return cell;
    }

    private static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding(5.0f);
        return cell;
    }

    private static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }
}
