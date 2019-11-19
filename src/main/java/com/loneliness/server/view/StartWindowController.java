package com.loneliness.server.view;

import com.loneliness.server.server.Server;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class StartWindowController {
    @FXML private Label quantityLabel=new Label();
    @FXML private TextArea console=new TextArea();

    @FXML void initialize(){
        QuantityService service = new QuantityService();
        AtomicInteger count = Server.getQuantity();
        service.setCount(count.get());
        service.setPeriod(Duration.seconds(5));
        service.setOnSucceeded(t -> quantityLabel.setText(String.valueOf(count.get())));
        service.start();
    }

    private static class QuantityService extends ScheduledService<Integer> {
        private IntegerProperty count = new SimpleIntegerProperty();

        public final void setCount(Integer value) {
            count.set(value);
        }

        public final Integer getCount() {
            return count.get();
        }

        public final IntegerProperty countProperty() {
            return count;
        }

        protected Task<Integer> createTask() {
            return new Task<Integer>() {
                protected Integer call() {
                    count.set(Server.getQuantity().get());
                    return getCount();
                }
            };
        }
    }
}
