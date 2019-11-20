package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.fxml_controller.Handler;
import com.loneliness.client.view.fxml_controller.WorkWithAlert;
import com.loneliness.entity.orders.OrderData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeOrderDataController implements Handler {

    @FXML
    private Stage dialogStage;
    private String action;
    private boolean okClicked = false;
    private OrderData orderData = new OrderData();

    @FXML
    private TextField orderManagerID;
    @FXML
    private TextField customerIDTextField;
    @FXML
    private TextField orderNameTextField;
    @FXML
    private DatePicker dateOfReceivingDatePicker;
    @FXML
    private DatePicker dateOfCompletionDatePicker;

    private OrderData.Status status;

    private OrderData.Payment payment;
    @FXML
    private ToggleGroup statusGroup = new ToggleGroup();
    @FXML
    private ToggleGroup paymentGroup = new ToggleGroup();

    @FXML
    private RadioMenuItem decorated;
    @FXML
    private RadioMenuItem componentCollection;
    @FXML
    private RadioMenuItem completed;
    @FXML
    private RadioMenuItem delivered;
    @FXML
    private RadioMenuItem canceled;
    @FXML
    private RadioMenuItem waitingForPayment;
    @FXML
    private RadioMenuItem issued;
    @FXML
    private RadioMenuItem refund;

    @FXML
    private RadioMenuItem paymentOnReceiptCashToSupplier;
    @FXML
    private RadioMenuItem paymentOnReceiptCOD;
    @FXML
    private RadioMenuItem paymentOnReceiptCashless;
    @FXML
    private RadioMenuItem prepaymentCOD;
    @FXML
    private RadioMenuItem prepaymentCash;
    @FXML
    private RadioMenuItem prepaymentCashless;

    public void setDialogStage(Stage dialogStage, String action) {
        this.dialogStage = dialogStage;
        this.action = action;

        decorated.setToggleGroup(statusGroup);
        componentCollection.setToggleGroup(statusGroup);
        completed.setToggleGroup(statusGroup);
        delivered.setToggleGroup(statusGroup);
        canceled.setToggleGroup(statusGroup);
        waitingForPayment.setToggleGroup(statusGroup);
        issued.setToggleGroup(statusGroup);
        refund.setToggleGroup(statusGroup);

        paymentOnReceiptCashToSupplier.setToggleGroup(paymentGroup);
        paymentOnReceiptCOD.setToggleGroup(paymentGroup);
        paymentOnReceiptCashless.setToggleGroup(paymentGroup);
        prepaymentCOD.setToggleGroup(paymentGroup);
        prepaymentCash.setToggleGroup(paymentGroup);
        prepaymentCashless.setToggleGroup(paymentGroup);
    }

    private String getStatus() {
        if (decorated.isSelected()) {
            return "ОФОРМЛЁН";
        } else if (componentCollection.isSelected()) {
            return "СБОР_КОМПЛЕКТУЮЩИХ";
        } else if (completed.isSelected()) {
            return "ВЫПОЛНЕН";
        } else if (delivered.isSelected()) {
            return "ДОСТАВЛЕНН";
        } else if (canceled.isSelected()) {
            return "ОТМЕНЕН";
        } else if (waitingForPayment.isSelected()) {
            return "ОЖИДАНИЕ_ОПЛАТЫ";
        } else if (issued.isSelected()) {
            return "ВЫДАНТ";
        } else if (refund.isSelected()) {
            return "ВОЗВРАТ";
        } else return "err";
    }

    private void setStatus(OrderData.Status status){
        if(status!=null)
        switch (status){
            case ОФОРМЛЁН:
                decorated.setSelected(true);
                break;
            case СБОР_КОМПЛЕКТУЮЩИХ:
                componentCollection.setSelected(true);
                break;
            case ВЫПОЛНЕН:
                completed.setSelected(true);
                break;
            case ДОСТАВЛЕН:
                delivered.setSelected(true);
                break;
            case ОТМЕНЕН:
                canceled.setSelected(true);
                break;
            case ОЖИДАНИЕ_ОПЛАТЫ:
                waitingForPayment.setSelected(true);
                break;
            case ВЫДАН:
                issued.setSelected(true);
                break;
            case ВОЗВРАТ:
                refund.setSelected(true);
                break;
        }
    }

    private String getPayment() {
        if (paymentOnReceiptCashToSupplier.isSelected()) {
            return "ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛИЧНЫМИ_ДОСТАВШИКУ";
        } else if (paymentOnReceiptCOD.isSelected()) {
            return "ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛОЖЕННЫЙ_ПЛАТЕЖ";
        } else if (paymentOnReceiptCashless.isSelected()) {
            return "ОПЛАТА_ПО_ПОЛУЧЕНИЮ_БЕЗНАЛИЧНЫЙ_РАСЧЁТ";
        } else if (prepaymentCash.isSelected()) {
            return "ПРЕДОПЛАТА_НАЛИЧНЫМИ";
        } else if (prepaymentCOD.isSelected()) {
            return "ПРЕДОПЛАТА_НАЛОЖЕННЫЙ_ПЛАТЕЖ";
        } else if (prepaymentCashless.isSelected()) {
            return "ПРЕДОПЛАТА_БЕЗНАЛИЧНЫЙ_РАСЧЁТ";
        } else return "err";
    }

    private void setPayment(OrderData.Payment payment){
        if(payment!=null)
        switch (payment){
            case ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛИЧНЫМИ_ДОСТАВШИКУ:
                paymentOnReceiptCashToSupplier.setSelected(true);
                break;
            case ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛОЖЕННЫЙ_ПЛАТЕЖ:
                paymentOnReceiptCOD.setSelected(true);
                break;
            case ОПЛАТА_ПО_ПОЛУЧЕНИЮ_БЕЗНАЛИЧНЫЙ_РАСЧЁТ:
                paymentOnReceiptCashless.setSelected(true);
                break;
            case ПРЕДОПЛАТА_НАЛИЧНЫМИ:
                prepaymentCash.setSelected(true);
                break;
            case ПРЕДОПЛАТА_НАЛОЖЕННЫЙ_ПЛАТЕЖ:
                prepaymentCOD.setSelected(true);
                break;
            case ПРЕДОПЛАТА_БЕЗНАЛИЧНЫЙ_РАСЧЁТ:
                prepaymentCashless.setSelected(true);
                break;
        }
    }

    @Override
    public boolean isInputValid() {
        Set<ConstraintViolation<Object>> errors = null;
        try {
            orderData.setCustomerId(Integer.parseInt(customerIDTextField.getText()));
            orderData.setOrderName(orderNameTextField.getText());
            orderData.setDateOfReceiving(dateOfReceivingDatePicker.getValue());
            orderData.setDateOfCompletion(dateOfCompletionDatePicker.getValue());
            orderData.setPayment(getPayment());
            orderData.setStatus(getStatus());
            orderData.setManagerID(Integer.parseInt(orderManagerID.getText()));
            errors = (Set<ConstraintViolation<Object>>)
                    CommandProvider.getCommandProvider().getCommand("ORDER_DATA_VALIDATION").execute(orderData);
            if (errors.size() == 0) {
                return true;
            } else {
                WorkWithAlert.getInstance().showAlert("Валидация заказа", "Ошибка", errors, dialogStage, "ERROR");
                return false;
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                    e.getExceptionMessage().toString(), dialogStage, "ERROR");
            return false;
        }
        catch (NumberFormatException ex){
            WorkWithAlert.getInstance().showAlert("Ошибка", "введены не верные данные",
                    "Была введена строка вместо числа", dialogStage, "ERROR");
            return false;
        }
    }

    @Override
    public void goBack() {
        okClicked = true;
        dialogStage.close();
    }

    @Override
    public void finishWork() {
        if (isInputValid()) {
            try {
                String answer="";
                switch (action) {
                    case "create":
                        answer=(String)CommandProvider.getCommandProvider().getCommand("CREATE_ORDER").execute(orderData);
                        break;
                    case "update":
                        answer=(String)CommandProvider.getCommandProvider().getCommand("UPDATE_ORDER").execute(orderData);
                        break;
                }
                if(WorkWithAlert.getInstance().showAnswer(answer,dialogStage,"Обновления данных" )){
                    goBack();
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                        e.getExceptionMessage().toString(), dialogStage, "ERROR");
            }
        }
    }

    public void setData(OrderData orderData) {
        this.orderData = orderData;
        customerIDTextField.setText(String.valueOf(orderData.getCustomerId()));
        orderNameTextField.setText(orderData.getOrderName());
        dateOfReceivingDatePicker.setValue(orderData.getDateOfReceiving());
        dateOfCompletionDatePicker.setValue(orderData.getDateOfCompletion());
        setStatus(orderData.getStatus());
        setPayment(orderData.getPayment());
    }
    public boolean isOkClicked() {
        return okClicked;
    }
}
