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
        return "";
    }
    public String getAdminStartWindow(){
        return "/AdminStartWindow.fxml";
    }
    public String getManagerStartWindow(){
        return "";
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
}
