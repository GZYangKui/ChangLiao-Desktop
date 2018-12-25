package cn.navigation.education.changliao.handler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

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
        writer(data);

    }

    private void initNetClient() {
        netClient = vertx.createNetClient();
        netClient.connect(config().getInteger(TCP_ORT), config().getString(SERVER), ar -> {
            if (!ar.succeeded()) {
                System.out.println("连接服务器失败:" + ar.cause().getMessage());
                return;
            }
            socket = ar.result();
            socket.handler(b -> {
                var resp = b.toJsonObject();
                System.out.println(b);
            });
            socket.exceptionHandler(e -> {
                System.out.println("连接断开:" + e.getMessage());
            });

        });


    }

    public void writer(JsonObject msg) {
        if (socket != null) {
            socket.end(msg.toBuffer());
        }
    }
}
