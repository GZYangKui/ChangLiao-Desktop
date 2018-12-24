package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.pages.MainPage;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

import static cn.navigation.education.changliao.base.BaseStage.STAGE_CONTEXT;

public class MainPageController extends BaseController implements Initializable {
    @FXML
    private VBox leftBox;
    @FXML
    private VBox leftBoxOne;
    @FXML
    private VBox leftBoxTwo;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton close;
    @FXML
    private JFXButton maximization;
    @FXML
    private HBox windowTopBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();
    }

    private void initView() {
        leftBoxOne.prefHeightProperty().bind(leftBox.heightProperty().multiply(0.7));
        leftBoxTwo.prefHeightProperty().bind(leftBox.heightProperty().multiply(0.3));
        close = (JFXButton) windowTopBar.lookup("#close");
        minimize = (JFXButton) windowTopBar.lookup("#minimize");
        maximization = (JFXButton) windowTopBar.lookup("#maximization");
    }

    private void event() {
        close.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        minimize.setOnAction(e -> STAGE_CONTEXT.get(MainPage.class.getName()).setIconified(true));
        maximization.setOnAction(e ->{
            BaseStage stage = (BaseStage) STAGE_CONTEXT.get(MainPage.class.getName());
            stage.setWindowSize();
        });
    }
}
