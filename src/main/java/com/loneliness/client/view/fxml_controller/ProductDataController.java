package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.PrimaryStage;

import com.loneliness.client.view.ViewException;
import com.loneliness.client.view.fxml_controller.change_data.ChangeProductDataController;
import com.loneliness.client.view.fxml_controller.change_data.ChangeUserDataController;
import com.loneliness.entity.Product;

import com.loneliness.entity.user.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDataController implements CRUD_Controller {
    private int id;
    private String accessRight;
    private String typeOfId;
    @FXML
    private Stage dialogStage;
    @FXML
    private Text productName;
    @FXML
    private Text productQuantity;
    @FXML
    private Text productUnitPrice;
    @FXML
    private Text allProductPrice;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    private ObservableList<Product> productsData = FXCollections.observableArrayList();

    public ObservableList<Product> setAndGetProductsData(ConcurrentHashMap<Integer, Product> map) {
        productsData.addAll(map.values());
        return productsData;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(String accessRight, int id) {
        if (!accessRight.equals("ADMIN") && !accessRight.equals("MANAGER")) {
            addButton.setVisible(false);
            addButton.setDisable(true);
            editButton.setVisible(false);
            editButton.setDisable(true);
            deleteButton.setVisible(false);
            deleteButton.setDisable(true);
        }
        this.accessRight = accessRight;
        this.id = id;
    }

    private void fillText(Product product) {
        if (product == null) {
            productName.setText("");
            productQuantity.setText("");
            productUnitPrice.setText("");
            allProductPrice.setText("");
        } else {
            productName.setText(product.getName());
            productQuantity.setText(String.valueOf(product.getQuantity()));
            productUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        }
    }

    @FXML
    private void initialize() {
        productTable.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, Product, newProduct) -> fillText(newProduct)));
        productIdColumn.setCellValueFactory(productIntegerCellDataFeatures -> productIntegerCellDataFeatures.getValue().
                idIntegerProperty().asObject());
        productNameColumn.setCellValueFactory(productStringCellDataFeatures -> productStringCellDataFeatures.getValue().
                nameStringProperty());
        Product product = null;
        fillText(product);

        update();
    }

    @Override
    public boolean goBack() {
        dialogStage.close();
        return true;
    }

    @Override
    public boolean update() {
        try {
            productsData.clear();
            String command = "RECEIVE_PRODUCT_GOODS";
            setAndGetProductsData((ConcurrentHashMap<Integer, Product>) CommandProvider.
                    getCommandProvider().getCommand("RECEIVE_PRODUCT_GOODS")
                    .execute(command));
            productTable.refresh();
            productTable.setItems(productsData);
            allProductPrice.setText(String.valueOf(CommandProvider.getCommandProvider().
                    getCommand("CALCULATE_SUM_OF_PRODUCT").execute(productsData)));

        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    @Override
    public boolean addHandler() {
        Stage dialogStage = null;
        try {
            dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                    getProductChangeData(), "Добавления данных");
            ChangeProductDataController changeProductDataController = WorkWithFXMLLoader.getInstance().getLoader().getController();
            changeProductDataController.setData(new Product());
            changeProductDataController.setDialogStage(dialogStage, "create",id);
            dialogStage.showAndWait();
            return changeProductDataController.isOkClicked();
        } catch (ViewException e) {
            WorkWithAlert.getInstance().showAlert("Добавления данных",
                    "Добавление невозможено", "Что то пошло не так",
                    this.dialogStage, "ERROR");
        }
        return false;

    }

    @Override
    public boolean deleteHandler() {
        int selectedIndex;
        String answer = "";
        String title = "";
        try {
            selectedIndex = productTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Product product = new Product();
                product.setId(productsData.get(selectedIndex).getId());
                answer = (String) CommandProvider.getCommandProvider().getCommand("DELETE_PRODUCT").execute(product);
                title = "Удаление товара ";

            } else {
                // Ничего не выбрано.
                WorkWithAlert.getInstance().showAlert("Удаление товара ",
                        "Удаление невозможно", "Выберите товар для удаления",
                        this.dialogStage, "ERROR");
            }

            WorkWithAlert.getInstance().showAnswer(answer, dialogStage, title);
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Нет корректного ответа от сервера", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }


        return false;
    }

    @Override
    public boolean changeHandler() {
       Product product = getSelectedProductModel();
        if (product != null) {
            Stage dialogStage = null;

            try {
                dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                        getChangeUSerData(), "Редактирование товара");
                ChangeProductDataController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                controller.setDialogStage(dialogStage, "update",id);
                controller.setData(product);
                dialogStage.showAndWait();
                return controller.isOkClicked();
            } catch (ViewException e) {
                WorkWithAlert.getInstance().showAlert("Неизвестная ошибка",
                        "Нарушение целостности программы", "Попробуйте повторить действие позже" +
                                " или принудительно закройте программу",
                        this.dialogStage, "ERROR");
            }

        }
        return false;
    }

    @Override
    public boolean searchHandler() {
        return false;
    }

    private Product getSelectedProductModel() {
        Product selectedItem=productTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            WorkWithAlert.getInstance().showAlert("Отсутствие выбора",
                    "Данные не выбраны", "Пожалуйста выберите данные в таблице.",
                    this.dialogStage, "ERROR");
            return null;
        } else {
            return selectedItem;
        }
    }
}
