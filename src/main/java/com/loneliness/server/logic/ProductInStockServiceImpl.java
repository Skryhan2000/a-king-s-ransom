package com.loneliness.server.logic;

import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ProductInStockServiceImpl implements Service<ProductInStock, ConcurrentHashMap<Integer, ProductInStock>,String,Transmission>{
    @Override
    public String create(ProductInStock obj) {
        return DAOFactory.getInstance().getProductInStockDAO().create(obj);
    }

    @Override
    public ProductInStock receive(ProductInStock obj) {
        return DAOFactory.getInstance().getProductInStockDAO().read(obj);
    }

    @Override
    public String update(ProductInStock obj) {
        return DAOFactory.getInstance().getProductInStockDAO().update(obj);
    }

    @Override
    public String delete(ProductInStock obj) {
        return DAOFactory.getInstance().getProductInStockDAO().delete(obj);
    }

    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAllElem() {
        return DAOFactory.getInstance().getProductInStockDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getProductInStockDAO().receiveAllInLimit(obj);
    }
    public ConcurrentHashMap<Integer, ProductInStock> findAllByNameAndQuantity(ProductInStock obj) {
        return DAOFactory.getInstance().getProductInStockDAO().findAllByNameAndQuantity(obj);
    }
}
