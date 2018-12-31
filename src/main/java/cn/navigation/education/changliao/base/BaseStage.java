package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.utils.DragUtil;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 所有窗口的基类
 */

public abstract class BaseStage extends Stage {
    public Scene scene;
    private boolean isMaxinize = false;

    public final static Map<String, Stage> STAGE_CONTEXT = new WeakHashMap<>();

    public BaseStage(String fxml) {
        STAGE_CONTEXT.put(this.getClass().getName(), this);
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource(fxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var topBox = root.lookup("#topBox");
        scene = new Scene(root);
        setScene(scene);
        setTitle("畅聊");
        initStyle(StageStyle.UNDECORATED);

        getIcons().add(AssetLoader.loadAssetImage("images/icon.png", 200, 200));
        setWidth(900);
        setHeight(600);
        show();
        //监听窗口拖动
        DragUtil.addDragListener(this, topBox);
    }

    /**
     * 调整窗体大小及位置
     */
    public void setWindowSize() {
        if (!isMaxinize) {
            Screen screen = Screen.getPrimary();
            setHeight(screen.getVisualBounds().getHeight());
            setWidth(screen.getVisualBounds().getWidth());
        } else {
            setHeight(600);
            setWidth(900);
        }
        centerOnScreen();
        isMaxinize = !isMaxinize;
    }
}
