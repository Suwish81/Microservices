package com.suwi.pdf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.suwi.pdf.fields.CustomTextField;
import com.suwi.pdf.fields.SubTotalEvent;
import com.suwi.pdf.listener.PDFTextFiledListner;
import com.suwi.pdf.model.Employee;
import com.suwi.pdf.model.Totals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PDFGenerator {


    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream test() {
        Totals totals = new Totals();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            PdfPTable table = new PdfPTable(5);
            table.setWidths(new int[]{1, 1, 1, 3, 3});
            // header
            table.addCell("Pos");
            table.addCell("Menge");
            table.addCell("Text");
            table.addCell("Einzerpreis");
            table.addCell("Summe");
            // footer
            PdfPCell cell = new PdfPCell(new Phrase("Subtotal"));
            cell.setColspan(4);
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setCellEvent(new SubTotalEvent(totals));
            table.addCell(cell);
            // definitions
            table.setHeaderRows(2);
            table.setFooterRows(1);
            // table body
            for (int r = 0; r < 50; ) {
                table.addCell(String.valueOf(++r));
                table.addCell("1");
                table.addCell("text");
                table.addCell("10.0");
                cell = new PdfPCell(new Phrase("10.0"));
                cell.setCellEvent(new SubTotalEvent(totals, 10));
                table.addCell(cell);
            }
            document.add(table);
            // extra footer
            table = new PdfPTable(5);
            table.setWidths(new int[]{1, 1, 1, 3, 3});
            cell = new PdfPCell(new Phrase("Grand total"));
            cell.setColspan(4);
            table.addCell(cell);
            table.addCell(String.valueOf(totals.total));
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());

    }

    public static ByteArrayInputStream employeePDFReport(List<Employee> employees) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Employee Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            TextField name1 = new TextField(writer, new Rectangle(100, 800, 200, 830), "idInput1");
            PdfFormField field1 = name1.getTextField();
            field1.setAdditionalActions(PdfName.C, PdfAction.javaScript("this.getField(\"idInput3\").value=this.getField(\"idInput1\").value", writer));

            //field1.put(PdfName.CO, new PdfNumber(1));
            writer.addAnnotation(field1);
            writer.addCalculationOrder(field1);

            TextField name2 = new TextField(writer, new Rectangle(210, 800, 300, 830), "idInput2");
            PdfFormField field2 = name2.getTextField();
            field2.setAdditionalActions(PdfName.C, PdfAction.javaScript("this.getField(\"idInput3\").value=this.getField(\"idInput1\").value+this.getField(\"idInput2\").value", writer));
            //field2.put(PdfName.CO, new PdfNumber(2));
            writer.addAnnotation(field2);
            writer.addCalculationOrder(field2);

            TextField name3 = new TextField(writer, new Rectangle(310, 800, 400, 830), "idInput3");
            PdfFormField field3 = name3.getTextField();
            field2.setAdditionalActions(PdfName.E, PdfAction.javaScript("", writer));
            //field2.put(PdfName.CO, new PdfNumber(3));
            writer.addAnnotation(field3);
            writer.addCalculationOrder(field3);

            PdfPTable table = new PdfPTable(5);
            // Add PDF Table Header ->
            Stream.of("ID", "Name","Sal1", "Sal2","Result").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });
            int i = -1;
            int j=0;
            String[][] codeArray = new String[employees.size()][3];
            for (Employee employee : employees) {
                i++;

                PdfPCell idCell = new PdfPCell(new Phrase(employee.getId().toString()));
                idCell.setPaddingLeft(1);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(employee.getFirstName() + ","+employee.getLastName()));
                idCell.setPaddingLeft(2);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);



                if(i!=employees.size()-1){
                    codeArray[i][0]="this.getField(\"fld3_"+i+"\").value=this.getField(\"fld1_"+i+"\").value + this.getField(\"fld2_"+i+"\").value;";
                    codeArray[i][1]="this.getField(\"fld3_"+i+"\").value=this.getField(\"fld1_"+i+"\").value + this.getField(\"fld2_"+i+"\").value;";
                    codeArray[i][2]=null;
                }else{
                    codeArray[i][0]="this.getField(\"fld1_"+i+"\").value= ";
                    codeArray[i][1]="this.getField(\"fld2_"+i+"\").value= ";
                    codeArray[i][2]="this.getField(\"fld3_"+i+"\").value= ";
                    for(int k=0;k<i;k++){
                        if(k>0){
                            codeArray[i][0]=codeArray[i][0]+"+";
                            codeArray[i][1]=codeArray[i][1]+"+";
                            codeArray[i][2]=codeArray[i][2]+"+";
                        }
                        codeArray[i][0]=codeArray[i][0]+" this.getField(\"fld1_"+k+"\").value";
                        codeArray[i][1]=codeArray[i][1]+" this.getField(\"fld2_"+k+"\").value";
                        codeArray[i][2]=codeArray[i][2]+" this.getField(\"fld3_"+k+"\").value";

                    }
                }

                if(i==2){
                    codeArray[i][2]="this.getField(\"fld3_2\").value=this.getField(\"fld1_0\").value + this.getField(\"fld2_1\").value;";
                }

                PdfPCell sal1Cell = new PdfPCell();
        		CustomTextField.SetEvent(sal1Cell,"fld1_" + i,codeArray[i][0]);
                table.addCell(sal1Cell);

                PdfPCell sal2Cell = new PdfPCell();
                CustomTextField.SetEvent(sal2Cell,"fld2_" + i,codeArray[i][1]);
				table.addCell(sal2Cell);


				PdfPCell calField = new PdfPCell();
				CustomTextField.SetEvent(calField,"fld3_" + i,codeArray[i][2],(i==employees.size()-1)?TextField.READ_ONLY:TextField.VISIBLE);
				table.addCell(calField);
			}
            document.add(table);

            document.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}