package com.loneliness.client.service;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;
public class ReportLogic {

    public String createReport(String reportName) throws ServiceException {
        try {
            Transmission transmission=new Transmission();
            switch (reportName) {
                case "QUARTERLY_REPORT":
                    return DAOFactory.getInstance().getReport().create((ConcurrentHashMap<Integer, ProductInStock>)CommandProvider.getCommandProvider().getCommand("RECEIVE_ALL_PRODUCT_IN_STOCK").execute(transmission));
                default:
                    return "ERROR нет такого вида отчета";
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        } catch (ControllerException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public String printReport(String report){
        return DAOFactory.getInstance().getReport().printReport(report);
    }

}
