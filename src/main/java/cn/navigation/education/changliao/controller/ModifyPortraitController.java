package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

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
    @FXML
    private HBox scaleBox;
    @FXML
    private HBox rotateBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        scaleBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.7));
        rotateBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.3));
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
