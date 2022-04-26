package com.suwi.pdf.fields;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.suwi.pdf.model.Totals;

public class SubTotalEvent implements PdfPCellEvent {

    Double price;
    Totals totals;

    public SubTotalEvent(Totals totals, double price) {
        this.totals = totals;
        this.price = price;
    }

    public SubTotalEvent(Totals totals) {
        this.totals = totals;
        price = null;
    }

    @Override
    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
        if (price == null) {
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(totals.subtotal)),
                    position.getLeft() + 2, position.getBottom() + 2, 0);
            totals.subtotal = 0;
            return;
        }
        totals.subtotal += price;
        totals.total += price;
    }

}
