package cn.navigation.education.changliao.base;


import io.vertx.core.json.JsonObject;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class BaseController {
    public final static Map<String, BaseController> CONTEXT = new WeakHashMap<>();

    public BaseController() {
        CONTEXT.put(this.getClass().getName(), this);
    }

    //更新ui界面
    public void updateUi(JsonObject data){}

    //初始化数据
    public void initData(Object data) {
    }


}
