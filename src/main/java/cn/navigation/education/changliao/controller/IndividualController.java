package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.component.PopStar;
import cn.navigation.education.changliao.model.Position;
import com.jfoenix.controls.JFXButton;
import io.vertx.core.json.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class IndividualController extends BaseController implements Initializable {

    @FXML
    private BorderPane container;
    @FXML
    private HBox topBox;
    @FXML
    private HBox content;
    @FXML
    private VBox leftBox;
    @FXML
    private VBox rightBox;
    @FXML
    private StackPane leftTopContent;
    @FXML
    private HBox leftBottomContent;
    @FXML
    private JFXButton star;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        //移除最大化
        topBox.getChildren().remove(1);
        leftBox.prefWidthProperty().bind(content.widthProperty().multiply(0.5));
        rightBox.prefWidthProperty().bind(content.widthProperty().multiply(0.5));
        leftBox.prefHeightProperty().bind(content.heightProperty());
        rightBox.prefHeightProperty().bind(content.heightProperty());
        leftTopContent.prefHeightProperty().bind(leftBox.heightProperty().multiply(0.7));
        leftBottomContent.prefHeightProperty().bind(leftBox.heightProperty().multiply(0.3));

    }

    private void event() {
        star.setOnMouseClicked(e->{
            var position = new Position();
            position.setY(e.getScreenY());
            position.setX(e.getScreenX());
            new PopStar(position).show(star.getScene().getWindow());
        });
    }

    @Override
    public void updateUi(JsonObject data) {

    }
}
