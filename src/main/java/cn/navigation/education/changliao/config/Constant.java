package cn.navigation.education.changliao.config;

import io.vertx.core.json.JsonObject;

public class Constant {
    public static final JsonObject VERTICLE_CONFIG = new JsonObject()
            .put("server", "http://polyglot.net.cn")
            .put("http_port", 80)
            .put("tcp_port", 3306);
    public static final String SERVER = "server";
    public static final String HTTP_PORT = "http_port";
    public static final String TCP_ORT = "tcp_port";
    public static final String LOGIN = "login";
    public static final String LOGIN_url ="/user/login";

}
