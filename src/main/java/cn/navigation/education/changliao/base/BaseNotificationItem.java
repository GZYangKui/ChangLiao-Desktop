package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.enums.NotificationCommand;
import cn.navigation.education.changliao.tool.AssetLoader;
import io.vertx.core.json.JsonObject;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;


public class BaseNotificationItem {

    protected HBox hBox = new HBox();
    protected HBox a = new HBox();
    protected HBox b = new HBox();
    protected VBox aa = new VBox();
    private Label title = new Label();
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Label msg;
    //是否处理
    private boolean isDeal = false;

    /**
     * @param topic   通知主题
     * @param msg     通知内容
     * @param data    数据
     * @param actions 该通知所需操作
     */

    public BaseNotificationItem(String topic, String msg, JsonObject data, NotificationCommand... actions) {

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
        hBox.getChildren().addAll(a, b);

        //处理选择事件
        choiceBox.setOnAction(e -> {
            isDeal = true;
            var select = choiceBox.getValue();
            var label = new Label(select);
            b.getChildren().remove(0, b.getChildren().size());
            b.getChildren().add(label);
        });

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

    public HBox getNotification() {
        return hBox;
    }
}
