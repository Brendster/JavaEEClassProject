package com.info5059.serverexercises.report;

import com.info5059.serverexercises.employee.Employee;
import com.info5059.serverexercises.employee.EmployeeRepository;
import com.info5059.serverexercises.expense.Expense;
import com.info5059.serverexercises.expense.ExpenseRepository;
import com.info5059.serverexercises.pdfexample.Generator;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ReportPDFGenerator - a class for creating dynamic expense report output in
 * PDF format using the iText 7 library
 *
 * @author Evan
 */
public abstract class ReportPDFGenerator extends AbstractPdfView {
    public static ByteArrayInputStream generateReport(String repid, ReportRepository reportRepository,
            EmployeeRepository employeeRepository, ExpenseRepository expenseRepository) throws IOException {
        Report report = new Report();
        Cell cell = new Cell();
        URL imageUrl = Generator.class.getResource("/static/images/Expenses.png");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        // Initialize PDF document to be written to a stream not a file
        PdfDocument pdf = new PdfDocument(writer);
        // Document is the main object
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        // add the image to the document
        PageSize pg = PageSize.A4;
        Image img = new Image(ImageDataFactory.create(imageUrl)).scaleAbsolute(120, 40)
                .setFixedPosition(pg.getWidth() / 2 - 60, 750);
        document.add(img);
        // now let's add a big heading
        document.add(new Paragraph("\n\n"));
        Locale locale = Locale.of("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        try {
            document.add(new Paragraph("\n"));
            Optional<Report> reportOption = reportRepository.findById(Long.parseLong(repid));
            document.add(new Paragraph("Report# " + repid).setFont(font).setFontSize(18).setBold()
                    .setMarginRight(pg.getWidth() / 2 - 75).setMarginTop(-10)
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n\n"));
            // add the employee info for the order here
            Table employeeTable = new Table(2).setWidth(new UnitValue(UnitValue.PERCENT, 30))
                    .setHorizontalAlignment(HorizontalAlignment.LEFT);
            Table expenseTable = new Table(4).setHorizontalAlignment(HorizontalAlignment.CENTER);
            Optional<Report> opt = reportRepository.findById(Long.parseLong(repid));
            if (opt.isPresent()) {
                report = opt.get();
                Optional<Employee> employeeCheck = employeeRepository.findById((report.getEmployeeid()));
                Employee selectedEmployee;
                if (employeeCheck.isPresent()) {
                    selectedEmployee = employeeCheck.get();
                    cell = new Cell().add(new Paragraph("Employee:")
                            .setFont(font)
                            .setFontSize(12)
                            .setBold())
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBorder(Border.NO_BORDER);
                    employeeTable.addCell(cell);
                    cell = new Cell().add(new Paragraph(selectedEmployee.getFirstname())
                            .setFont(font)
                            .setFontSize(12)
                            .setBold())
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                    employeeTable.addCell(cell);
                    cell = new Cell().add(new Paragraph()
                            .setFont(font)
                            .setFontSize(12)
                            .setBold())
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBorder(Border.NO_BORDER);
                    employeeTable.addCell(cell);
                    cell = new Cell().add(new Paragraph(selectedEmployee.getLastname())
                            .setFont(font)
                            .setFontSize(12)
                            .setBold())
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                    employeeTable.addCell(cell);
                    cell = new Cell().add(new Paragraph()
                            .setFont(font)
                            .setFontSize(12)
                            .setBold())
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBorder(Border.NO_BORDER);
                    employeeTable.addCell(cell);
                    cell = new Cell().add(new Paragraph(selectedEmployee.getEmail())
                            .setFont(font)
                            .setFontSize(12)
                            .setBold())
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                    employeeTable.addCell(cell);
                }
                BigDecimal tot = new BigDecimal(0.0);
                cell = new Cell().add(new Paragraph("Id")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                expenseTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Date Incurred")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                expenseTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Description")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                expenseTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Amount")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                expenseTable.addCell(cell);
                for (ReportItem line : report.getItems()) {
                    Optional<Expense> optx = expenseRepository.findById(line.getExpenseid());
                    if (optx.isPresent()) {
                        Expense expense = optx.get();
                        tot = tot.add(expense.getAmount(), new MathContext(8, RoundingMode.UP));

                        cell = new Cell().add(new Paragraph(Long.toString(expense.getId()))
                                .setFont(font)
                                .setFontSize(12)
                                .setBold())
                                .setTextAlignment(TextAlignment.CENTER)
                                .setMinWidth(25);
                        expenseTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(expense.getDateincurred())
                                .setFont(font)
                                .setFontSize(12)
                                .setBold())
                                .setTextAlignment(TextAlignment.CENTER)
                                .setMinWidth(150);
                        expenseTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(expense.getDescription())
                                .setFont(font)
                                .setFontSize(12)
                                .setBold())
                                .setTextAlignment(TextAlignment.LEFT)
                                .setMinWidth(200);
                        expenseTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(formatter.format(expense.getAmount()))
                                .setFont(font)
                                .setFontSize(12)
                                .setBold())
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setMinWidth(100);
                        expenseTable.addCell(cell);
                    }
                }
                cell = new Cell(1, 3).add(new Paragraph("Total:")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold()
                        .setTextAlignment(TextAlignment.RIGHT))
                        .setBorder(Border.NO_BORDER);
                expenseTable.addCell(cell);
                cell = new Cell().add(new Paragraph(formatter.format(tot))
                        .setFont(font)
                        .setFontSize(12)
                        .setBold()
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setBackgroundColor(ColorConstants.YELLOW));
                expenseTable.addCell(cell);
            }
            document.add(employeeTable);
            document.add(new Paragraph("\n\n"));
            document.add(expenseTable);
            document.add(new Paragraph("\n\n"));
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
            //document.add(new Paragraph(dateFormatter.format(LocalDateTime.now()))
            document.add(new Paragraph(dateFormatter.format(opt.get().getDatecreated()))
                    .setTextAlignment(TextAlignment.CENTER));
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        // finally send stream back to the controller
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
