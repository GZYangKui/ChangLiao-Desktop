package cn.navigation.education.changliao;

import cn.navigation.education.changliao.pages.MainPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new MainPage();
    }
}
