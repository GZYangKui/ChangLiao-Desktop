package cn.navigation.education.changliao.handler;

import io.vertx.core.AbstractVerticle;

public class HttpHandler extends AbstractVerticle {
    @Override
    public void start() {
        vertx.eventBus().consumer(this.getClass().getName(),ar->{

        });

    }
}
