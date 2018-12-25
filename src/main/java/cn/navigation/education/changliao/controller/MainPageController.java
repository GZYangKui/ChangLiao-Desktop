package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseVerticle;
import cn.navigation.education.changliao.base.BaseLeftContent;
import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.component.CollectionList;
import cn.navigation.education.changliao.component.MailList;
import cn.navigation.education.changliao.component.MessageList;
import cn.navigation.education.changliao.pages.MainPage;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static cn.navigation.education.changliao.base.BaseStage.STAGE_CONTEXT;

public class MainPageController extends BaseVerticle implements Initializable {
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
    @FXML
    private Pagination pagination;
    @FXML
    private JFXButton chat;
    @FXML
    private JFXButton addressList;
    @FXML
    private JFXButton collection;

    private List<BaseLeftContent> lists = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();
    }

    private void initView() {
        leftBoxOne.prefHeightProperty().bind(leftBox.heightProperty().multiply(0.7));
        leftBoxTwo.prefHeightProperty().bind(leftBox.heightProperty().multiply(0.3));
        pagination.prefHeightProperty().bind(leftBox.heightProperty());
        close = (JFXButton) windowTopBar.lookup("#close");
        minimize = (JFXButton) windowTopBar.lookup("#minimize");
        maximization = (JFXButton) windowTopBar.lookup("#maximization");
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.setPageFactory(e -> lists.get(e).getContent());
        lists.add(new MessageList());
        lists.add(new MailList());
        lists.add(new CollectionList());

    }

    private void event() {
        close.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        minimize.setOnAction(e -> STAGE_CONTEXT.get(MainPage.class.getName()).setIconified(true));
        maximization.setOnAction(e -> {
            BaseStage stage = (BaseStage) STAGE_CONTEXT.get(MainPage.class.getName());
            stage.setWindowSize();
        });

        chat.setOnAction(e -> pagination.setCurrentPageIndex(0));
        addressList.setOnAction(e -> pagination.setCurrentPageIndex(1));
        collection.setOnAction(e -> pagination.setCurrentPageIndex(2));

    }
}
