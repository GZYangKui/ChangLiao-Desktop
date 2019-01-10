package cn.navigation.education.changliao.config;

import io.vertx.core.json.JsonObject;

public class Constant {
    public static final JsonObject VERTICLE_CONFIG = new JsonObject()
            .put("server", "polyglot.net.cn")
            .put("http_port", 80)
            .put("tcp_port", 7373);
    public static final String SERVER = "server";
    public static final String HTTP_PORT = "http_port";
    public static final String TCP_ORT = "tcp_port";
    public static final String LOGIN = "login";
    public static final String LOGIN_URL = "/user/login";
    public static final String TYPE = "type";
    public static final String SUBTYPE = "subtype";
    public static final String ACCOUNT = "account";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String VERSION = "version";
    public static final String CURRENT_VERSION = "0.2";
    public static final String USER = "user";
    public static final String ID = "id";
    public static final String END = "\r\n";
    public static final String FRIENDS = "friends";
    public static final String NICKNAME = "nickname";
    public static final String MESSAGE = "message";
    public static final String FROM = "from";
    public static final String BODY = "body";
    public static final String TEXT = "text";
    public static final String TO = "to";
    public static final String UUID = "uuid";
    public static final String STATUS = "status";
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
    public static final JsonObject CURRENT_ACCOUNT = new JsonObject();
    public static final String FRIEND = "friend";
    public static final String REQUEST = "request";
    public static final String CURRENT_CONTENT = "currentContent";
    public static final String EXIT ="exit";
    public static final String COUNTER_CLOCKWISE = "counterClockwise";


}
