package cn.navigation.education.changliao.base;

import io.vertx.core.AbstractVerticle;

import java.util.Map;
import java.util.WeakHashMap;

public class BaseVerticle extends AbstractVerticle {
    public final static Map<String, BaseVerticle> CONTEXT = new WeakHashMap<>();

    public BaseVerticle() {
        CONTEXT.put(this.getClass().getName(),this);
    }


}
