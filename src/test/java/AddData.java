import com.github.javafaker.Faker;
import com.loneliness.entity.*;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.CommandProvider;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class AddData {
    private Faker faker=new Faker();
    private int quantity=5;
    @Test public void addUser(){
        UserData userData;
        int c=0;
        for(int i=0;i<quantity;i++){
            userData=new UserData();
            userData.setLogin(faker.funnyName().name());
            userData.setPassword(faker.internet().password());
            UserData.Type type;
            switch (faker.number().numberBetween(0,3)){
                case 0:
                    type= UserData.Type.ADMIN;
                    break;
                case 2:
                    type= UserData.Type.MANAGER;
                    break;
                default:type= UserData.Type.NO_TYPE;
            }
            userData.setType(type);
            userData.setSecretQuestion("Любимый персонаж");
            userData.setSecretAnswer(faker.rickAndMorty().character());
            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_USER").
                    execute(userData)).equals("Пользователь успешно создан")){
                c++;
            }

        }
        Assert.assertEquals(c,quantity);
    }
    @Test public void addProviders(){

        ProviderData providerData;
        int c=0;
        for(int i=0;i<quantity;i++){
            providerData=new ProviderData();
            providerData.setName(faker.company().name());
            providerData.setRating(faker.number().numberBetween(0,10));
            providerData.setLocation(faker.rickAndMorty().location());
            providerData.setEmail(faker.internet().emailAddress());
            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_PROVIDER").
                    execute(providerData)).equals("Успешное создание")){
                c++;
            }

        }
        Assert.assertEquals(c,quantity);
    }
    @Test public void addProductInStock() throws ParseException {
        ConcurrentHashMap<Integer,ProviderData> providerDataMap=(ConcurrentHashMap<Integer,ProviderData>)
                CommandProvider.getCommandProvider().getCommand("RECEIVE_ALL_PROVIDERS").execute(null);
        ArrayList<Integer> ids=new ArrayList<>();
        for (ProviderData d :
                providerDataMap.values()) {
            ids.add(d.getId());
        }
        int c=0;
        ProductInStock product;
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.YEAR, -1);
        for (int i = 0; i < quantity; i++) {
            product=new ProductInStock();
            product.setProvider_ID(ids.get(faker.number().numberBetween(0,ids.size())));
            product.setUnitPrice(faker.number().randomDouble(100,1,1000));
            product.setQuantity(faker.number().numberBetween(1,1000));

            java.sql.Date date= new java.sql.Date(faker.date().between(calendar.getTime(),
                    new Date()).getTime());
            product.setReceipt_date(date.toLocalDate());
            product.setName(faker.commerce().productName());

            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_PRODUCT_IN_STOCK").
                    execute( product)).equals("Успешное создание")){
                c++;
            }
        }
        Assert.assertEquals(c,quantity);
    }
    @Test public void addProductInOrders(){
        ConcurrentHashMap<Integer, OrderData> orderDataMap=(ConcurrentHashMap<Integer,OrderData>)CommandProvider.
                getCommandProvider().getCommand("RECEIVE_ALL_ORDERS").execute(null);
        ArrayList<Integer> ids=new ArrayList<>();
        for (OrderData d :
                orderDataMap.values()) {
            ids.add(d.getId());
        }
        Product product;
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.YEAR, -1);
        int c=0;
        for (int i = 0; i < quantity; i++) {
            product=new Product();
            product.setOrderId(ids.get(faker.number().numberBetween(0,ids.size())));
            product.setUnitPrice(faker.number().randomDouble(100,1,1000));
            product.setQuantity(faker.number().numberBetween(1,1000));
            product.setName(faker.commerce().productName());
            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_PRODUCT").
                    execute(product)).equals("Успешное создание")){
                c++;
            }
        }
        Assert.assertEquals(c,quantity);
    }
    @Test public void addCustomer(){
        CustomerData customerData;
        int c=0;
        for(int i=0;i<quantity;i++){
           customerData=new CustomerData();
           customerData.setName(faker.company().name());
           customerData.setEmail(faker.internet().emailAddress());
           customerData.setLocation(faker.rickAndMorty().location());
           customerData.setNumberOfOrders(faker.number().numberBetween(0,50));

            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_CUSTOMER_DATA").
                    execute(customerData)).equals("Успешное создание")){
                c++;
            }

        }
        Assert.assertEquals(c,quantity);
    }
    @Test public void addCustomerRepresent(){
        ConcurrentHashMap<Integer,CustomerData> customerDataMap=(ConcurrentHashMap<Integer,CustomerData>)CommandProvider.
                getCommandProvider().getCommand("RECEIVE_ALL_CUSTOMERS_DATA").execute(null);
        ArrayList<Integer> customerIds=new ArrayList<>();
        for (CustomerData d :
                customerDataMap.values()) {
            customerIds.add(d.getId());
        }
        ConcurrentHashMap<Integer,UserData> UserDataMap=(ConcurrentHashMap<Integer,UserData>)CommandProvider.
                getCommandProvider().getCommand("RECEIVE_ALL_USERS").execute(null);
        ArrayList<Integer> managersId=new ArrayList<>();
        for (UserData userData :
                UserDataMap.values()) {
            if(userData.getType().toString().equals("CLIENT"))
            managersId.add(userData.getId());
        }
        CustomerRepresentative customerRepresentative;
        int c=0;
        for (Integer customerId :
                customerIds) {
            customerRepresentative=new CustomerRepresentative();
            customerRepresentative.setUserID(managersId.get(faker.number().numberBetween(0,managersId.size())));
            customerRepresentative.setCustomerID(customerId);
            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_CUSTOMER_REPRESENTATIVE").
                    execute(customerRepresentative)).equals("Успешное создание")){
                c++;
            }
        }
        Assert.assertEquals(c,customerIds.size());
    }
    @Test public void addManagerEmail(){
        ConcurrentHashMap<Integer,UserData> UserDataMap=(ConcurrentHashMap<Integer,UserData>)CommandProvider.
                getCommandProvider().getCommand("RECEIVE_ALL_USERS").execute(null);
        ArrayList<Integer> managersId=new ArrayList<>();
        for (UserData userData :
                UserDataMap.values()) {
            if(userData.getType().toString().equals("MANAGER"))
                managersId.add(userData.getId());
        }
        ConcurrentHashMap<Integer,String> managerData=new ConcurrentHashMap<>();
        for (Integer id :
                managersId) {
            managerData.put(id, faker.internet().emailAddress());
        }
        Assert.assertEquals("Успешное создание",(String)CommandProvider.
                getCommandProvider().getCommand("ADD_MANAGER_EMAIL").execute(managerData));
    }
    @Test public void addOrders(){
        ConcurrentHashMap<Integer,CustomerData> customerDataMap=(ConcurrentHashMap<Integer,CustomerData>)CommandProvider.
                getCommandProvider().getCommand("RECEIVE_ALL_CUSTOMERS_DATA").execute(null);
        ArrayList<Integer> customerIds=new ArrayList<>();
        for (CustomerData d :
                customerDataMap.values()) {
            customerIds.add(d.getId());
        }
        ConcurrentHashMap<Integer,UserData> UserDataMap=(ConcurrentHashMap<Integer,UserData>)CommandProvider.
                getCommandProvider().getCommand("RECEIVE_ALL_USERS").execute(null);
        ArrayList<Integer> managersId=new ArrayList<>();
        for (UserData userData :
                UserDataMap.values()) {
            if(userData.getType().toString().equals("MANAGER"))
                managersId.add(userData.getId());
        }
        Calendar pastCalendar = new GregorianCalendar();
        pastCalendar.add(Calendar.YEAR, -1);
        Calendar futureCalendar=new GregorianCalendar();
        futureCalendar.add(Calendar.MONTH,1);
        OrderData orderData;
        int c=0;
        for (int i = 0; i < quantity; i++) {
            orderData=new OrderData();
            orderData.setCustomerId(customerIds.get(faker.number().numberBetween(0,customerIds.size())));
            orderData.setOrderName(faker.commerce().productName());
            java.sql.Date pastDate= new java.sql.Date(faker.date().between(pastCalendar.getTime(),new Date()).getTime());
            orderData.setDateOfReceiving(pastDate.toLocalDate());

            java.sql.Date futureDate= new java.sql.Date(faker.date().between(new Date(),
                    futureCalendar.getTime()).getTime());
            orderData.setDateOfCompletion(futureDate.toLocalDate());
            OrderData.Status status;
            switch (faker.number().numberBetween(0,7)){
                case 0:
                    status=OrderData.Status.ВОЗВРАТ;
                    break;
                case 1:
                    status=OrderData.Status.ВЫДАН;
                    break;
                case 2:
                    status=OrderData.Status.ВЫПОЛНЕН;
                    break;
                case 3:
                    status=OrderData.Status.ДОСТАВЛЕН;
                    break;
                case 4:
                    status=OrderData.Status.ОЖИДАНИЕ_ОПЛАТЫ;
                    break;
                case 5:
                    status=OrderData.Status.ОТМЕНЕН;
                    break;
                case 6:
                    status=OrderData.Status.ОФОРМЛЁН;
                    break;
                default:status=OrderData.Status.СБОР_КОМПЛЕКТУЮЩИХ;

            }
            orderData.setStatus(status);
            OrderData.Payment payment;
            switch (faker.number().numberBetween(0,5)){
                case 0:
                    payment= OrderData.Payment.ОПЛАТА_ПО_ПОЛУЧЕНИЮ_БЕЗНАЛИЧНЫЙ_РАСЧЁТ;
                    break;
                case 1:
                    payment= OrderData.Payment.ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛИЧНЫМИ_ДОСТАВШИКУ;
                    break;
                case 2:
                    payment= OrderData.Payment.ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛОЖЕННЫЙ_ПЛАТЕЖ;
                    break;
                case 3:
                    payment= OrderData.Payment.ПРЕДОПЛАТА_БЕЗНАЛИЧНЫЙ_РАСЧЁТ;
                    break;
                case 4:
                    payment= OrderData.Payment.ПРЕДОПЛАТА_НАЛИЧНЫМИ;
                    break;
                default:  payment= OrderData.Payment.ПРЕДОПЛАТА_НАЛОЖЕННЫЙ_ПЛАТЕЖ;
            }
            orderData.setPayment(payment);
            orderData.setManagerID(managersId.get(faker.number().numberBetween(0,managersId.size())));
            if(((String)CommandProvider.getCommandProvider().getCommand("CREATE_ORDER").
                    execute(orderData)).equals("Успешное создание")){
                c++;
            }
        }
        Assert.assertEquals(c,quantity);
    }
}
