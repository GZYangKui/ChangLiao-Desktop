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
    @FXML
    private HBox topBoxOne;
    @FXML
    private HBox topBoxTwo;
    private JFXButton close;

    @Override
    public void updateUi(JsonObject data) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        topBoxOne.prefWidthProperty().bind(topBox.widthProperty().multiply(0.5));
        topBoxTwo.prefWidthProperty().bind(topBox.widthProperty().multiply(0.5));
        topBoxTwo.getChildren().remove(0, 2);
        close = (JFXButton) topBoxTwo.getChildren().get(0);
    }

    private void event() {
        //点击close关闭窗口
        close.setOnAction(e -> close.getScene().getWindow().fireEvent(new Event(WindowEvent.WINDOW_CLOSE_REQUEST)));
    }
}
