package cn.navigation.education.changliao;

import cn.navigation.education.changliao.handler.ServerHandler;
import cn.navigation.education.changliao.pages.Login;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cn.navigation.education.changliao.config.Constant.VERTICLE_CONFIG;

public class MainVerticle {
    private final static Logger LOGGER = LogManager.getLogger(MainVerticle.class.getName());

    public static Vertx vertx = Vertx.vertx();


    public MainVerticle() {
        LOGGER.error("hello");
        //启动javafx线程
        Platform.startup(()->new Login());
        //部署Vertice
        deploymentVertical();
    }

    public void deploymentVertical() {
        var options = new DeploymentOptions().setConfig(VERTICLE_CONFIG);
        vertx.deployVerticle(new ServerHandler(), options);
    }

    static public void main(String... args) { new MainVerticle(); }
}
