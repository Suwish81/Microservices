package com.suwi.pdf.fields;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.suwi.pdf.listener.PDFTextFiledListner;

import java.io.IOException;

public class CustomTextField implements PdfPCellEvent {

    protected String fieldname;
    protected String code;
    protected PdfPCell cell;
    public TextField textField;
    int index;
    int options;

    public static void SetEvent(PdfPCell cell,String fieldname,String code,int options) {

        CustomTextField cellEvent = new CustomTextField(cell,fieldname,code,options);
        cell.setCellEvent(cellEvent);

    }
    public static void SetEvent(PdfPCell cell,String fieldname,String code) {

        SetEvent(cell,fieldname,code,0);

    }


    private CustomTextField(PdfPCell cell,String fldName,String code,int options) {
        this.fieldname = fldName;
        this.cell=cell;
        this.code=code;
        this.options=options;


    }
    @Override
    public void cellLayout(PdfPCell pdfPCell, Rectangle rectangle, PdfContentByte[] pdfContentBytes) {
        final PdfWriter writer = pdfContentBytes[PdfPTable.TEXTCANVAS].getPdfWriter();
        this.textField = new TextField(writer, rectangle, fieldname);
        if(this.options>0){
            this.textField.setOptions(this.options);
        }

        System.out.println(fieldname);
        try {
            final PdfFormField field = textField.getTextField();
            if(code!=null){
                System.out.println(code);
                field.setAdditionalActions(PdfName.C,PdfAction.javaScript(code, writer));
            }

            writer.addAnnotation(field);
            writer.addCalculationOrder(field);


        } catch (final IOException ioe) {
            throw new ExceptionConverter(ioe);
        } catch (final DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }


}
