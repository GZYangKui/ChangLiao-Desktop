package cn.navigation.education.changliao.handler;


import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.base.MessageHandler;
import cn.navigation.education.changliao.component.ChatDialog;
import cn.navigation.education.changliao.component.MailList;
import cn.navigation.education.changliao.component.MessageList;
import cn.navigation.education.changliao.component.NotificationPane;
import cn.navigation.education.changliao.controller.LoginController;
import cn.navigation.education.changliao.controller.MainPageController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import javafx.application.Platform;

import java.util.concurrent.CompletableFuture;

import static cn.navigation.education.changliao.base.BaseContent.BASE_CONTENT;
import static cn.navigation.education.changliao.base.BaseController.CONTEXT;
import static cn.navigation.education.changliao.base.BaseLeftContent.BASE_LEFT_CONTENT_MAP;
import static cn.navigation.education.changliao.config.Constant.*;


public class TcpHandler extends AbstractVerticle {
    private NetClient netClient;
    private NetSocket socket;

    @Override
    public void start() throws Exception {


        CompletableFuture.runAsync(this::initNetClient);


        vertx.eventBus().consumer(this.getClass().getName(), ar -> {
            var data = (JsonObject) ar.body();

            var type = data.getString(TYPE);
            switch (type) {
                case USER:
                    userAccount(data, ar);
                    break;
                default:
                case MESSAGE:
                    userMessage(data, ar);
                    break;
            }

        });
    }

    private void userMessage(JsonObject data, Message it) {
        writerMessage(data, it::reply);
    }

    private void userAccount(JsonObject data, Message it) {
        var subtype = data.getString(SUBTYPE);
        if (subtype.equals(LOGIN)) {
            login(data, it);
        }

    }

    private void login(JsonObject data, Message it) {
        writerMessage(data, it::reply);
    }

    private void initNetClient() {
        //设置10s超时
        NetClientOptions options = new NetClientOptions()
                //连接超时
                .setConnectTimeout(10000)
                //设置重连次数
                .setReconnectAttempts(10)

                .setLogActivity(true);

        netClient = vertx.createNetClient(options);
        netClient.connect(config().getInteger(TCP_ORT), config().getString(SERVER), ar -> {
            if (!ar.succeeded()) {
                return;
            }
            socket = ar.result();
            socket.handler(this::handler);
            socket.exceptionHandler(e -> {
                System.out.println("连接断开:" + e.getMessage());
            });

        });

    }

    /**
     * 处理服务器返回来的数据
     *
     * @param buffer
     */
    private void handler(Buffer buffer) {
        var data = buffer.toJsonObject();
        var type = data.getString(TYPE);
        var subtype = data.getString(SUBTYPE);
        //将数据转发到登陆界面中去
        if (type.equals(USER) && subtype.equals(LOGIN)) {
            BaseController login = CONTEXT.get(LoginController.class.getName());
            Platform.runLater(() -> login.updateUi(data));
            return;
        }
        /**
         * 如果接受到的数据类型为Message则分发消息
         */

        if (type.equals(MESSAGE)) {
            deliverMessage(data);
        }

        if (type.equals(FRIEND)) {
            deliverNotification(data);
        }

    }

    /**
     * 分发消息到各个窗口
     */
    private void deliverMessage(JsonObject data) {

        var controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
        var messageList = (MessageList)BASE_LEFT_CONTENT_MAP.get(MessageList.class.getName());
        //将消息转发到主界面中去
        controller.updateUi(data);
        //将消息更新到消息列表
        messageList.updateUi(data);

        //将消息发送自当前聊天框
        var content = BASE_CONTENT.get(CURRENT_CONTENT);

        if (content instanceof ChatDialog) {

            ChatDialog chatDialog = (ChatDialog) content;
            var from = data.getString(FROM);
            //如果消息不是来自于当前聊天好友或者当前账号，不是则不做任何事
            if (!from.equals(chatDialog.getId()) && !from.equals(CURRENT_ACCOUNT.getString(ID))) {

                return;
            }
            chatDialog.updateUi(data);
        }

    }

    /**
     * 分发通知
     * @param data
     */
    private void deliverNotification(JsonObject data){
        var controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());

        var mailList = (MailList) BASE_LEFT_CONTENT_MAP.get(MailList.class.getName());

        controller.addNotification(data);

        var content = BASE_CONTENT.get(CURRENT_CONTENT);

        if (!(content instanceof NotificationPane)){
            mailList.updateNotification();
            return;
        }
        var pane = (NotificationPane)content;
        pane.updateUi(data);

    }


    /**
     * 将消息发送给服务器
     *
     * @param msg
     */

    public void writerMessage(JsonObject msg, MessageHandler<JsonObject> handler) {
        var result = new JsonObject();
        result.put(STATUS, SUCCESS);
        try {
            if (socket != null) {
                socket.write(msg.toBuffer().appendString(END));
            }
        } catch (Exception e) {
            System.out.println("发送消息失败:" + e.getMessage());
            result.put(STATUS, FAILED);
            result.put(MESSAGE, "发送消息失败:" + e.getMessage());
        }

        handler.handler(result);

    }
}
