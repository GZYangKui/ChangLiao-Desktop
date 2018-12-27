package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.MainContentBase;
import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.enums.MessageType;
import io.vertx.core.json.JsonObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static cn.navigation.education.changliao.config.Constant.NICKNAME;

/**
 * 聊天对话框
 */
public class ChatDialog extends MainContentBase {
    private VBox content = new VBox();

    public ChatDialog(JsonObject o) {
        super("fxml/items/chat_dialog.fxml");
        initView(o);
    }


    public void initView(JsonObject o) {
        Label nickName = (Label) container.lookup("#nickName");
        nickName.setText(o.getString(NICKNAME));
        VBox topBox = (VBox) container.lookup("#topBox");


        var messageDialog = (ScrollPane) container.lookup("#messageDialog");
        messageDialog.setContent(content);
        content.getChildren().add(new Message("hello,jack!", MessageType.TEXT, MessageSource.OWN).getPane());
        content.getChildren().add(new Message("hello,tom!", MessageType.TEXT, MessageSource.FRIEND).getPane());


        VBox inputBox = (VBox) container.lookup("#inputBox");
        HBox bottomActionLeft = (HBox) container.lookup("#bottomActionLeft");
        HBox bottomActionRight = (HBox) container.lookup("#bottomActionRight");
        TextArea inputArea = (TextArea) container.lookup("#inputArea");


        bottomActionLeft.prefWidthProperty().bind(inputBox.widthProperty().multiply(0.7));
        bottomActionRight.prefWidthProperty().bind(inputBox.widthProperty().multiply(0.3));

        topBox.prefHeightProperty().bind(container.heightProperty().multiply(0.1));
        messageDialog.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        inputBox.prefHeightProperty().bind(container.heightProperty().multiply(0.1));

    }
}
