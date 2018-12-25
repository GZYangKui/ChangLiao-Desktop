package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.scene.layout.BorderPane;

public abstract class MainContentBase {
    protected BorderPane container;


    public MainContentBase(String fxml) {
        var root = AssetLoader.loadLayout(fxml);
        container = (BorderPane) root.lookup("#container");
    }

    public BorderPane getContent() {
        return container;
    }
}
