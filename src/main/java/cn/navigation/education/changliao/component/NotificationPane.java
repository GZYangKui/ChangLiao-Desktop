package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.BaseNotificationItem;
import cn.navigation.education.changliao.base.BaseContent;
import cn.navigation.education.changliao.controller.MainPageController;
import cn.navigation.education.changliao.enums.NotificationCommand;
import com.jfoenix.controls.JFXListView;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;
import static cn.navigation.education.changliao.config.Constant.*;


public class NotificationPane extends BaseContent {
    private StackPane stackPane;
    private JFXListView notificationList;

    public NotificationPane() {
        super("fxml/items/notification_pane.fxml");
        stackPane = (StackPane) container.lookup("#stack");
        notificationList = (JFXListView) container.lookup("#notificationList");
        notificationList.setStyle("-fx-background-color: #F5F5F5;");
        initData();
    }

    private void initData() {
        var controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
        controller.handleNotification(this::addNotification);
    }

    private void addNotification(JsonObject n) {
        if (n.getString(TYPE).equals(FRIEND) && n.getString(SUBTYPE).equals(REQUEST)) {

            var notification = new BaseNotificationItem(
                    n.getString(FROM),
                    n.getString(MESSAGE),
                    n,
                    NotificationCommand.AGREE,
                    NotificationCommand.REFUSE
            );
            notificationList.getItems().add(notification.getNotification());
        }
        if (notificationList.getItems().size() != 0 && !notificationList.isVisible()) {
            notificationList.setVisible(true);
        }
    }

    public void updateUi(JsonObject data) {
        Platform.runLater(() -> {
                    addNotification(data);
                }
        );
    }
}
