package com.loneliness.client.dao;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReportDAO {

    String pathForSaving = "Data\\TestResult.pdf";
    String pathForPattern = "Data\\report.jrxml";

    public String create(ConcurrentHashMap<Integer, ProductInStock> data) throws DAOException {
        try {
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data.values());
            Map<String, Object> parameters = new HashMap<String, Object>();
            File reportPattern = new File(pathForPattern);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathForSaving);
            return "Отчёт успешно создан";
        } catch (JRException e) {
            throw new DAOException("Ошибка создание отчета по товаром на складе ", "ReportDAO " + e.getMessage());
        }
    }
}
