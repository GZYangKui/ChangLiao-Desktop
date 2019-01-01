package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.MainVerticle;
import cn.navigation.education.changliao.base.BaseLeftContent;
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
import static cn.navigation.education.changliao.base.BaseLeftContent.BASE_LEFT_CONTENT_MAP;
import static cn.navigation.education.changliao.config.Constant.*;


/**
 * 聊天对话框
 */
public class ChatDialog extends MainContentBase {
    private VBox content = new VBox();
    private final String id;
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
            var msg = inputArea.getText().trim();
            if (msg.equals("")) {
                return;
            }
            //设置滚动面板滚动到底部
            messageDialog.setVvalue(1);
            System.out.println(msg + "kkk");
            var message = new JsonObject();
            message.put(TYPE, MESSAGE).put(SUBTYPE, TEXT)
                    .put(TO, id).put(BODY, msg)
                    .put(UUID, java.util.UUID.randomUUID().toString())
                    .put(VERSION, CURRENT_VERSION);

            MainVerticle.vertx.eventBus().send(TcpHandler.class.getName(), message, rs -> {
                if (!rs.succeeded()) {
                    System.out.println("消息发送失败:" + rs.cause());
                    return;
                }
                var result = (JsonObject) rs.result().body();

                //发送成功
                if (result.getString(STATUS).equals(SUCCESS)) {

                    Platform.runLater(() -> {
                        //添加条目
                        content.getChildren().add(new TextMessage(msg, MessageType.TEXT,
                                MessageSource.OWN, messageDialog).getMessage());
                        //清除输入框
                        inputArea.clear();

                        //将消息更新到消息列表中去
                        BaseLeftContent messageList = BASE_LEFT_CONTENT_MAP.get(MessageList.class.getName());
                        messageList.updateUi(message.put(FROM, id));
                        //将消息更新到主界面中去储蓄
                        MainPageController c = (MainPageController) CONTEXT.get(MainPageController.class.getName());
                        c.updateUi(message.put(FROM, CURRENT_ACCOUNT.getString(ID)));
                    });
                    return;
                }

                System.out.println("发送失败:" + result.getString(MESSAGE));
            });


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


        //如果消息不是来自于当前聊天好友或者当前账号，不是则不做任何事
        if (!from.equals(id) && !from.equals(CURRENT_ACCOUNT.getString(ID))) {
            return;
        }

        var body = m.getString(BODY);

        Platform.runLater(() -> {
            TextMessage message;
            if (!from.equals(CURRENT_ACCOUNT.getString(ID))) {
                message = new TextMessage(body, MessageType.TEXT, MessageSource.FRIEND, messageDialog);
            } else {
                message = new TextMessage(body, MessageType.TEXT, MessageSource.OWN, messageDialog);
            }
            content.getChildren().add(message.getMessage());
        });


    }
}
