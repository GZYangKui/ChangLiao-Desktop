package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.enums.NotificationCommand;
import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;


public abstract class BaseNotificationItem {

    protected HBox hBox = new HBox();
    protected HBox a = new HBox();
    protected HBox b = new HBox();
    protected VBox aa = new VBox();
    private Label title = new Label();
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Label msg;

    /**
     * @param topic   通知主题
     * @param msg     通知内容
     * @param actions 该通知所需操作
     */

    public BaseNotificationItem(String topic, String msg, NotificationCommand... actions) {

        title.setText(topic);
        var image = AssetLoader.loadAssetImage("images/icon.png", 30, 30);
        var icon = new ImageView(image);

        aa.getChildren().add(title);
        if (msg != null) {
            this.msg = new Label(msg);
            aa.getChildren().add(this.msg);
        }

        a.getChildren().add(icon);
        a.getChildren().add(aa);
        a.setSpacing(10);
        a.setAlignment(Pos.CENTER_LEFT);
        //设置动作
        Arrays.stream(actions).forEach(this::setAction);
        b.setAlignment(Pos.CENTER_RIGHT);
        b.getChildren().add(choiceBox);
        a.prefWidthProperty().bind(hBox.widthProperty().multiply(0.5));
        b.prefWidthProperty().bind(hBox.widthProperty().multiply(0.49));
        hBox.getChildren().addAll(a,b);

    }

    private void setAction(NotificationCommand command) {
        switch (command) {
            case AGREE:
                choiceBox.getItems().add("同意");
                break;
            case REFUSE:
                choiceBox.getItems().add("拒绝");
                break;
            default:
                choiceBox.getItems().add("查看");
        }

    }
    public HBox getNotification(){
        return hBox;
    }
}
