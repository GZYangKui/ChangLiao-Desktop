package cn.navigation.education.changliao.base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public abstract class BaseLeftContent {
    public TextField input;
    public JFXButton search;
    public BorderPane container;
    public JFXListView messageList;
    public HBox topBox;

    public BaseLeftContent() {
        initView();
        customUi();
        event();
        loadData();
    }
    private void initView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/items/message_address_list.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        container = (BorderPane) root.lookup("#container");

        topBox = (HBox) root.lookup("#topBox");
        search = (JFXButton) root.lookup("#search");
        input = (TextField) root.lookup("#input");
        messageList = (JFXListView) root.lookup("#contentList");

    }

    public abstract BorderPane getContent();

    public  void event(){ }

    protected void customUi(){ }

    protected void loadData(){ }

    public void initData(Object data){}
}
