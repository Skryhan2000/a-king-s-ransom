package com.loneliness.server.logic;

import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

public class ProductInStockServiceImpl implements Service{
    @Override
    public Object create(Object obj) {
        return DAOFactory.getInstance().getProductInStockDAO().create(obj);
    }

    @Override
    public Object receive(Object obj) {
        return DAOFactory.getInstance().getProductInStockDAO().read(obj);
    }

    @Override
    public Object update(Object obj) {
        return DAOFactory.getInstance().getProductInStockDAO().update(obj);
    }

    @Override
    public Object delete(Object obj) {
        return DAOFactory.getInstance().getProductInStockDAO().delete(obj);
    }

    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getProductInStockDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getProductInStockDAO().receiveAllInLimit((Transmission) obj);
    }
    public Object findAllByNameAndQuantity(Object obj) {
        return DAOFactory.getInstance().getProductInStockDAO().findAllByNameAndQuantity((ProductInStock) obj);
    }
}
