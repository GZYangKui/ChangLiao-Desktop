package cn.navigation.education.changliao;

import cn.navigation.education.changliao.handler.ServerHandler;
import cn.navigation.education.changliao.pages.Login;
import cn.navigation.education.changliao.controller.LoginController;
import cn.navigation.education.changliao.controller.MainPageController;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import javafx.application.Platform;


import static cn.navigation.education.changliao.config.Constant.VERTICLE_CONFIG;

public class MainVerticle {

    private Vertx vertx = Vertx.vertx();


    public MainVerticle() {
        //启动javafx线程
        Platform.startup(()->new Login());
        //部署Vertice
        deploymentVertical();
    }

    public void deploymentVertical() {
        var options = new DeploymentOptions().setConfig(VERTICLE_CONFIG);
        vertx.deployVerticle(new ServerHandler(), options);
        vertx.deployVerticle(new LoginController());
        vertx.deployVerticle(new MainPageController());
    }

    static public void main(String... args) { new MainVerticle(); }
}
