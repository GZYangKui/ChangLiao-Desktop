package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.MainContentBase;
import com.jfoenix.controls.JFXListView;
import javafx.scene.layout.StackPane;

public class NotificationPane extends MainContentBase {
    private StackPane stackPane;
    private JFXListView notificationList;

    public NotificationPane() {
        super("fxml/items/notification_pane.fxml");
        stackPane = (StackPane) container.lookup("#stack");
        notificationList = (JFXListView) container.lookup("#notificationList");
        notificationList.setVisible(true);
        notificationList.getItems().add(new FriendRequest().getNotification());
    }
}
