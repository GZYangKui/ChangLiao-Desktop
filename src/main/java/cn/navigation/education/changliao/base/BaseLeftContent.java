package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.tool.AssetLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import io.vertx.core.json.JsonObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class BaseLeftContent {
    public TextField input;
    public JFXButton search;
    public BorderPane container;
    public JFXListView messageList;
    public HBox topBox;


    public static final Map<String, BaseLeftContent> BASE_LEFT_CONTENT_MAP = new WeakHashMap<>();

    public BaseLeftContent() {
        initView();
        customUi();
        event();
        loadData();

        BASE_LEFT_CONTENT_MAP.put(this.getClass().getName(), this);
    }

    private void initView() {
        Parent root = AssetLoader.loadLayout("fxml/items/message_address_list.fxml");

        container = (BorderPane) root.lookup("#container");

        topBox = (HBox) root.lookup("#topBox");
        search = (JFXButton) root.lookup("#search");
        input = (TextField) root.lookup("#input");
        messageList = (JFXListView) root.lookup("#contentList");

    }

    public abstract BorderPane getContent();

    public void event() {
    }

    protected void customUi() {
    }

    protected void loadData() {
    }

    public void initData(Object data) {
    }

    public void updateUi(JsonObject d) {
    }
}
