package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.enums.StageCloseStrategy;
import cn.navigation.education.changliao.pages.MainPage;
import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.utils.DragUtil;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;

/**
 * 所有窗口的基类
 */

public abstract class BaseStage extends Stage {
    public Scene scene;
    private boolean isMinimize = false;

    private JFXButton close;

    private final double WIDTH;
    private final double HEIGHT;

    public BaseStage(String fxml, double width, double height) {
        WIDTH = width;
        HEIGHT = height;
        Parent root = AssetLoader.loadLayout(fxml);
        var topBox = root.lookup("#topBox");
        scene = new Scene(root);
        setScene(scene);
        setTitle("畅聊");
        initStyle(StageStyle.UNDECORATED);
        getIcons().add(AssetLoader.loadAssetImage("images/icon.png", 200, 200));
        setWidth(WIDTH);
        setHeight(HEIGHT);
        registerEvent((HBox) topBox);
        show();

    }

    /**
     * 管理窗口状态
     * pr:最小化、最大化、关闭
     *
     * @param topBox
     */
    private void registerEvent(HBox topBox) {
        close = (JFXButton) topBox.lookup("#close");
        var minimize = (JFXButton) topBox.lookup("#minimize");
        var maximization = (JFXButton) topBox.lookup("#maximization");

        Optional.ofNullable(minimize).ifPresent(e -> e.setOnAction(e1 -> setIconified(true)));
        Optional.ofNullable(maximization).ifPresent(max -> max.setOnAction(e -> {
            setWindowSize();
        }));

        //窗口拖动
        DragUtil.addDragListener(this, topBox);
        //默认只是关闭窗口，不退出虚拟机
        setStageCloseStrategy(StageCloseStrategy.CLOSE);
    }

    /**
     * 设置窗口关闭模式
     *
     * @param strategy
     */
    public void setStageCloseStrategy(StageCloseStrategy strategy) {
        if (Objects.isNull(strategy)) {
            return;
        }
        switch (strategy) {
            case EXIT:
                close.setOnAction(e -> System.exit(1));
                break;
            default:
                close.setOnAction(e -> this.close());

        }
    }


    /**
     * 调整窗体大小及位置
     */
    public void setWindowSize() {
        if (!isMinimize) {
            Screen screen = Screen.getPrimary();
            setHeight(screen.getVisualBounds().getHeight());
            setWidth(screen.getVisualBounds().getWidth());
        } else {
            setHeight(HEIGHT);
            setWidth(WIDTH);
        }
        centerOnScreen();
        isMinimize = !isMinimize;
    }
}
