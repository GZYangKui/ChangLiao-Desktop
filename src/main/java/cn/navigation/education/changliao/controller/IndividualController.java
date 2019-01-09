package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.component.PopStar;
import cn.navigation.education.changliao.model.Position;
import cn.navigation.education.changliao.pages.ModifyPortrait;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;


public class IndividualController extends BaseController implements Initializable {
    @FXML
    private HBox topBox;
    @FXML
    private StackPane topContent;
    @FXML
    private HBox bottomContent;
    @FXML
    private JFXButton star;
    @FXML
    private JFXButton modify;
    @FXML
    private BorderPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        topContent.prefHeightProperty().bind(container.heightProperty().multiply(0.7));
        bottomContent.prefHeightProperty().bind(container.heightProperty().multiply(0.3));

    }

    private void event() {
        star.setOnMouseClicked(e -> {
            var position = new Position();
            position.setY(e.getScreenY());
            position.setX(e.getScreenX());
            new PopStar(position).show(star.getScene().getWindow());
        });
        modify.setOnAction(e -> new ModifyPortrait());
    }
}
