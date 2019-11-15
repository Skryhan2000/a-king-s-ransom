package com.loneliness.client.dao;

import com.loneliness.client.PathManager;
import com.loneliness.entity.ProductInStock;
import com.lowagie.text.pdf.PdfDocument;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.lucene.document.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReportDAO {
enum Report{
    QUARTERLY_REPORT
}
    public String create(ConcurrentHashMap<Integer, ProductInStock> data) throws DAOException {
        try {
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data.values());
            Map<String, Object> parameters = new HashMap<String, Object>();
            File reportPattern = new File(PathManager.getInstance().getPathForPatternProductInStockReport());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, PathManager.getInstance().getPathForSavingProductInStockReport());
            return "Отчёт успешно создан";
        } catch (JRException e) {
            throw new DAOException("Ошибка создание отчета по товаром на складе ", "ReportDAO " + e.getMessage());
        }
    }
    public String printReport(String report) {
        // TODO: 14.11.2019 протестить
        try {
            PDDocument pdf;
            switch (report) {
                case "QUARTERLY_REPORT":
                    pdf = PDDocument.load(new File(PathManager.getInstance().getPathForSavingProductInStockReport()));
                    break;
                default:return "ERROR Неизвестный тип оичета";
            }

            PrintService printer = choosePrinter();
            if (printer == null) {
                return "Принтер не выбран";
            } else {

                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintService(printer);

                job.setPageable(new PDFPageable(pdf));
                job.print();
                return "Файл ушел в печать";

            }
        } catch (PrinterException | IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
    private  PrintService choosePrinter() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        if(printJob.printDialog()) {
            return printJob.getPrintService();
        }
        else {
            return null;
        }
    }
}
