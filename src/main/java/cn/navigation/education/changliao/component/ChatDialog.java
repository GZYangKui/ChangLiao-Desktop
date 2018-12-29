package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.MainVerticle;
import cn.navigation.education.changliao.base.MainContentBase;
import cn.navigation.education.changliao.controller.MainPageController;
import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.enums.MessageType;
import cn.navigation.education.changliao.handler.TcpHandler;
import com.jfoenix.controls.JFXButton;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import static cn.navigation.education.changliao.base.BaseController.CONTEXT;
import static cn.navigation.education.changliao.config.Constant.*;


/**
 * 聊天对话框
 */
public class ChatDialog extends MainContentBase {
    private VBox content = new VBox();
    private String id;
    private ScrollPane messageDialog;

    public ChatDialog(String id) {
        super("fxml/items/chat_dialog.fxml");
        this.id = id;
        initView(id);
        initData(id);
    }


    public void initView(String id) {
        Label nickName = (Label) container.lookup("#nickName");
        nickName.setText(id);
        VBox topBox = (VBox) container.lookup("#topBox");


        messageDialog = (ScrollPane) container.lookup("#messageDialog");
        messageDialog.setContent(content);
        messageDialog.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(10);

        VBox inputBox = (VBox) container.lookup("#inputBox");
        HBox bottomActionLeft = (HBox) container.lookup("#bottomActionLeft");
        HBox bottomActionRight = (HBox) container.lookup("#bottomActionRight");
        var inputArea = (TextArea) container.lookup("#inputArea");
        var send = (JFXButton) container.lookup("#send");


        bottomActionLeft.prefWidthProperty().bind(inputBox.widthProperty().multiply(0.7));
        bottomActionRight.prefWidthProperty().bind(inputBox.widthProperty().multiply(0.3));

        topBox.prefHeightProperty().bind(container.heightProperty().multiply(0.1));
        messageDialog.prefHeightProperty().bind(container.heightProperty().multiply(0.8));
        inputBox.prefHeightProperty().bind(container.heightProperty().multiply(0.1));


        inputArea.setOnKeyPressed(e -> {
            var code = e.getCode().getCode();
            //按下enter键,发送消息
            if (code == 10) {
                send.fire();
            }
        });

        send.setOnAction(e -> {
            var msg = inputArea.getText();
            if (msg.trim().equals("")) {
                return;
            }
            //设置滚动面板滚动到底部
            messageDialog.setVvalue(1);
            var message = new JsonObject();
            message.put(TYPE, MESSAGE).put(SUBTYPE, TEXT)
                    .put(TO, id).put(BODY, msg)
                    .put(UUID, java.util.UUID.randomUUID().toString())
                    .put(VERSION, CURRENT_VERSION);

            MainVerticle.vertx.eventBus().send(TcpHandler.class.getName(), message);
            content.getChildren().add(new Message(msg, MessageType.TEXT,
                    MessageSource.OWN, messageDialog).getPane());
            inputArea.clear();

        });


    }

    private void initData(String id) {
        MainPageController controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
        controller.handlerMessage(id, ar -> {
            ar.forEach(v -> {
                var vv = (JsonObject) v;
                updateUi(vv);
            });
        });
    }

    @Override
    public void updateUi(JsonObject m) {
        var from = m.getString(FROM);
        //如果消息不是来自于当前聊天好友，不是则不做任何事
        if (!from.equals(id)) {
            return;
        }
        var body = m.getString(BODY);

        Platform.runLater(() -> {
            var message = new Message(body, MessageType.TEXT, MessageSource.FRIEND, messageDialog);
            content.getChildren().add(message.getPane());
        });


    }
}
