package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.launcher.Client;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class Reconnect {
    private final static Reconnect instance =new Reconnect();
    private AtomicBoolean cancel = new AtomicBoolean(false);
    private Reconnect(){
        ReconnectService service = new ReconnectService();
        service.setPeriod(Duration.seconds(2));
        service.setOnSucceeded(t -> {
            if((Boolean) t.getSource().getValue()){
                cancel.set(service.cancel());
            }

        });
        service.start();}

    public AtomicBoolean getCancel() {
        return cancel;
    }

    public static Reconnect getInstance() {
        return instance;
    }
    public void reconnect(){



    }
    private static class ReconnectService extends ScheduledService<Boolean> {
        private BooleanProperty connection = new SimpleBooleanProperty();

        public final void setConnection(Boolean value) {
            connection.set(value);
        }

        public final Boolean getConnection() {
            return connection.get();
        }

        public boolean isConnection() {
            return connection.get();
        }

        public BooleanProperty connectionProperty() {
            return connection;
        }

        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {
                protected Boolean call() {
                    connection.set(Client.reconnect());
                    return getConnection();
                }
            };
        }
    }
}
