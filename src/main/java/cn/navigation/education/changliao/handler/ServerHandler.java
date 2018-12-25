package cn.navigation.education.changliao.handler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;

import static cn.navigation.education.changliao.config.Constant.*;


public class ServerHandler extends AbstractVerticle {
    private NetClient netClient;
    @Override
    public void start() throws Exception {
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
            login(data,it);
        }

    }
    private void login(JsonObject data,Message it){
        if (netClient==null){
            //netClient.connect();
        }
    }
}
