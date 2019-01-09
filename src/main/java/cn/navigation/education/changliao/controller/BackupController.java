package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import com.jfoenix.controls.JFXButton;
import io.vertx.core.json.JsonObject;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BackupController extends BaseController implements Initializable {
    @FXML
    private HBox topBox;

    @Override
    public void updateUi(JsonObject data) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
    }

    private void event() {

    }
}
