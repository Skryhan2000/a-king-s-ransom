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
}
