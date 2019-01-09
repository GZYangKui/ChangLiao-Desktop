package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModifyPortraitController extends BaseController implements Initializable {
    @FXML
    private HBox topBox;
    @FXML
    private JFXButton localUpload;
    @FXML
    private JFXButton selectedPortrait;
    @FXML
    private JFXButton upload;
    @FXML
    private JFXButton cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        topBox.getChildren().remove(1);
    }

    private void event() {
        cancel.setOnAction(e -> {
            var w = cancel.getScene().getWindow();
            if (Objects.nonNull(w)) {
                w.hide();
            }
        });
    }
}
