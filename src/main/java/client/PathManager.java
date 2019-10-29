

public class PathManager {
    private static PathManager instance = new PathManager();

    public static PathManager getInstance() {
        return instance;
    }

    private PathManager() {
    }
    private String authorisationFormController="view.fxml_controller.AuthorisationFormController";

    public String getAuthorisationFormController() {
        return authorisationFormController;
    }
}
