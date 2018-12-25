package cn.navigation.education.changliao.base;

import io.vertx.core.AbstractVerticle;

import java.util.Map;
import java.util.WeakHashMap;

public class BaseController extends AbstractVerticle {
    public final static Map<String, BaseController> CONTEXT = new WeakHashMap<>();

    public BaseController() {
        CONTEXT.put(this.getClass().getName(),this);
    }


}
