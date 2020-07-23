/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
import java.util.List;

/**
 * Ideas : https://github.com/venkatvkpt/Invoice-PDF-ITEXT-
 * Edited with some modification
 * @author ADI
 * 
 */
abstract class ReceiptMaker {
    
    protected int GenerateReceipt(String trans_id){
        System.out.println("Please override this");
        return 0;
    }
    
    protected int GenerateReceipt(String[] trans_data){
        System.out.println("Please override this");
        return 0;
    }
    protected abstract PdfPCell getBillHeaderCell(String text);

    protected abstract PdfPCell getBillRowCell(String text);

    protected static PdfPCell getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }
}
