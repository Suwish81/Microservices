package com.suwi.pdf.listener;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import java.util.EventListener;

public class PDFTextFiledListner implements ElementListener {

    PdfPCell cell;

    public PDFTextFiledListner(PdfPCell cell){
        this.cell=cell;
    }


    @Override
    public boolean add(Element element) throws DocumentException {
        this.cell.setPhrase(new Phrase("sdasda"));

        System.out.println("###############");

        return false;
    }
}
