package cn.navigation.education.changliao;

import cn.navigation.education.changliao.handler.NetworkHandler;
import cn.navigation.education.changliao.pages.Login;
import cn.navigation.education.changliao.pages.MainPage;
import com.sun.javafx.tk.Toolkit;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import javafx.application.Platform;


import static cn.navigation.education.changliao.config.Constant.VERTICLE_CONFIG;

public class MainVerticle {

    public static final Vertx vertx = Vertx.vertx();


    public MainVerticle() {
        //启动javafx线程
        Platform.startup(()->new Login());
        //部署Vertice
        deploymentVertical();
    }

    public void deploymentVertical() {
        var options = new DeploymentOptions(VERTICLE_CONFIG);
        vertx.deployVerticle(new NetworkHandler(), options);
    }

    static public void main(String... args) { new MainVerticle(); }
}
