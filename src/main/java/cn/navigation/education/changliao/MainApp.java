package cn.navigation.education.changliao;

import cn.navigation.education.changliao.handler.HttpHandler;
import cn.navigation.education.changliao.handler.TcpHandler;
import cn.navigation.education.changliao.pages.Login;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import javafx.application.Application;
import javafx.stage.Stage;

import static cn.navigation.education.changliao.config.Constant.*;

public class MainApp extends Application {

    public static Vertx vertx = Vertx.vertx();


    @Override
    public void start(Stage stage) {
        new Login();
        deploymentVertical();

    }

    public void deploymentVertical() {
        var options = new DeploymentOptions().setConfig(VERTICLE_CONFIG);
        vertx.deployVerticle(new TcpHandler(), options);
        vertx.deployVerticle(new HttpHandler(), options);
    }

    @Override
    public void stop() {
        System.exit(1);
    }
}
