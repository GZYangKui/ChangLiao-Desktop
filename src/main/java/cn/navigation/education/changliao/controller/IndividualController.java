package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.pages.MainPage;
import com.jfoenix.controls.JFXButton;
import io.vertx.core.json.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public class IndividualController extends BaseController implements Initializable {

    private JFXButton minimize;
    private JFXButton close;
    private JFXButton maximization;
    @FXML
    private BorderPane container;
    @FXML
    private HBox topBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }
    private void initView(){
        //移除最大化
        topBox.getChildren().remove(1);

    }
    private void event(){
    }

    @Override
    public void updateUi(JsonObject data) {

    }
}
