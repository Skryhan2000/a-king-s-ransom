package com.loneliness.client;

public class PathManager {
    private static PathManager instance = new PathManager();

    public static PathManager getInstance() {
        return instance;
    }

    private PathManager() {
    }

    public String getAuthorisationFormController() {
        return "/AuthorisationForm.fxml";
    }
    public String getClientStartWindow(){
        return "/CustomerStartWindow.fxml";
    }
    public String getAdminStartWindow(){
        return "/AdminStartWindow.fxml";
    }
    public String getManagerStartWindow(){
        return "/ManagerStartMenu.fxml";
    }
    public String getForgetYourPasswordCase(){
        return "/ForgetPasswordCase.fxml";
    }
    public String getRegistrationForm(){
        return "/RegistrationForm.fxml";
    }
    public String getChangeUSerData(){return "/ChangeUserData.fxml";}
    public String getSearchUserData(){return "/SearchWindow.fxml";}
    public String getChangeProviderData(){return "/ChangeProviderData.fxml";}
    public String getChangeOrderData(){return "/ChangeOrderData.fxml";}
    public String getChangeProductInStock(){return "/ChangeProductInStock.fxml";}
    public String getChangeCustomerData(){return "/ChangeCustomerData.fxml";}
    public String getSupplierRatingChart(){return "/SupplierRatingChart.fxml";}
    public String getProductChart(){return "/ProductChart.fxml";}
    public String getSearchForTheBestSupplier(){return "/SearchForTheBestSupplier.fxml";}
    public String getProductChangeData(){return "/ProductChangeData.fxml";}
    public String getProductData(){return "/ProductData.fxml";}
    public String getPathForSavingProductInStockReport(){return "Data\\TestResult.pdf";}
    public String getPathForPatternProductInStockReport(){return "Data\\report.jrxml";}


}
