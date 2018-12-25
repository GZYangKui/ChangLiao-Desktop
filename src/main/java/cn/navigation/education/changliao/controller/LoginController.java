package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.pages.Login;
import cn.navigation.education.changliao.tool.AssetLoader;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import static cn.navigation.education.changliao.base.BaseStage.STAGE_CONTEXT;

public class LoginController extends BaseController implements Initializable {
    @FXML
    private HBox topBox;
    @FXML
    private HBox titleBox;
    @FXML
    private HBox topControllerBar;
    @FXML
    private Pagination pagination;

    private JFXButton close;
    private JFXButton minimize;

    private BorderPane QRCodeLoginPame;
    private BorderPane accountLogin;
    private Hyperlink switchLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();
    }

    public void initView() {
        titleBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.5));
        topControllerBar.prefWidthProperty().bind(topBox.widthProperty().multiply(0.5));
        //移除最大化图标
        topControllerBar.getChildren().remove(1);
        close = (JFXButton) topControllerBar.lookup("#close");
        minimize = (JFXButton) topControllerBar.lookup("#minimize");
        QRCodeLoginPame = (BorderPane) AssetLoader.loadLayout("fxml/items/qr_code_login.fxml").lookup("#container");
        accountLogin = (BorderPane) AssetLoader.loadLayout("fxml/items/account_login.fxml").lookup("#container");
        switchLogin = (Hyperlink) QRCodeLoginPame.lookup("#switchLogin");
        pagination.setPageFactory(this::pageFactory);
    }

    private BorderPane pageFactory(int index) {
        if (index == 0) {
            return QRCodeLoginPame;
        }else{
            return accountLogin;
        }
    }

    private void event() {
        close.setOnAction(e -> {
            Platform.exit();
            System.exit(1);
        });
        minimize.setOnAction(e -> {
            var stage = STAGE_CONTEXT.get(Login.class.getName());
            stage.setIconified(true);
        });
        /**
         * 切换到账号登陆
         */
        switchLogin.setOnAction(e->pagination.setCurrentPageIndex(1));
    }
}
