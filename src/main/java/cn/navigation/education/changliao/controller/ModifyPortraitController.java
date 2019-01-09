package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.tool.AssetLoader;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import org.controlsfx.control.MaskerPane;

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
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        scaleBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.7));
        rotateBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.3));
        gc = canvas.getGraphicsContext2D();
        drawBg(AssetLoader.loadAssetImage("images/header.jpg"));
    }

    private void drawBg(Image image) {
        gc.drawImage(image, 0.0, 0.00, canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(1);
        gc.strokeOval(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

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
