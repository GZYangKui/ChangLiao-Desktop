package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.tool.AssetLoader;
import io.vertx.core.json.JsonObject;
import javafx.scene.layout.BorderPane;

import java.util.Map;
import java.util.WeakHashMap;

import static cn.navigation.education.changliao.config.Constant.CURRENT_CONTENT;

public abstract class BaseContent {
    protected BorderPane container;

    public static final Map<String, BaseContent> BASE_CONTENT = new WeakHashMap<>();

    public BaseContent(String fxml) {

        BASE_CONTENT.put(CURRENT_CONTENT, this);

        var root = AssetLoader.loadLayout(fxml);
        container = (BorderPane) root.lookup("#container");
    }

    public BorderPane getContent() {
        return container;
    }

    public void updateUi(JsonObject m) { }

}
