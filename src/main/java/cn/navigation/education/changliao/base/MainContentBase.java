package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.tool.AssetLoader;
import io.vertx.core.json.JsonObject;
import javafx.scene.layout.BorderPane;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class MainContentBase {
    protected BorderPane container;

    public static final Map<String,MainContentBase> MAIN_CONTENT_BASE_MAP = new WeakHashMap<>();

    public MainContentBase(String fxml) {
        MAIN_CONTENT_BASE_MAP.put(this.getClass().getName(),this);
        var root = AssetLoader.loadLayout(fxml);
        container = (BorderPane) root.lookup("#container");
    }

    public BorderPane getContent() {
        return container;
    }

    public void updateUi(JsonObject m){}

}
