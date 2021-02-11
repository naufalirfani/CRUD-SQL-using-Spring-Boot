package com.javan.tugas4.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.javan.tugas4.data.DataEmployeePrint;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class UserPDFExporter {
    private List<DataEmployeePrint> listEmployee;

    public UserPDFExporter(List<DataEmployeePrint> listEmployee) {
        this.listEmployee = listEmployee;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Id", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Nama", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Posisi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Perusahaan", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (DataEmployeePrint employee : listEmployee) {
            table.addCell(String.valueOf(employee.id));
            table.addCell(employee.nama);
            table.addCell(employee.posisi);
            table.addCell(employee.perusahaan);
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Employee", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {0.5f, 3.5f, 1.5f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
