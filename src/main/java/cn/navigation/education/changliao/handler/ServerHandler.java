package cn.navigation.education.changliao.handler;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.controller.LoginController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import javafx.application.Platform;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;
import static cn.navigation.education.changliao.config.Constant.*;


public class ServerHandler extends AbstractVerticle {
    private NetClient netClient;
    private NetSocket socket;

    @Override
    public void start() throws Exception {
        initNetClient();
        vertx.eventBus().consumer(this.getClass().getName(), ar -> {
            var data = (JsonObject) ar.body();
            var type = data.getString(TYPE);
            switch (type) {
                case ACCOUNT:
                    userAccount(data, ar);
                    break;
                default:
            }

        });
    }

    private void userAccount(JsonObject data, Message it) {
        var subtype = data.getString(SUBTYPE);
        if (subtype.equals(LOGIN)) {
            login(data, it);
        }

    }

    private void login(JsonObject data, Message it) {
        var d = new JsonObject();
        var result = new JsonObject();
        data.put(TYPE, USER)
                .put(SUBTYPE, LOGIN)
                .put(ID, data.getString(USERNAME))
                .put(PASSWORD, data.getString(PASSWORD))
                .put(VERSION, CURRENT_CURSION);
        writerMessage(data);

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

    }

    public void writerMessage(JsonObject msg) {
        if (socket != null) {
            socket.write(msg.toBuffer().appendString(END));
        }
    }
}
