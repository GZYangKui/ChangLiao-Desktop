package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.enums.StageCloseStrategy;
import cn.navigation.education.changliao.enums.StageComponet;
import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.utils.DragUtil;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;


/**
 *
 * 所有窗口的基类
 *
 */

public abstract class BaseStage extends Stage {
    public Scene scene;
    private boolean isMinimize = false;

    private JFXButton close;

    private final double WIDTH;
    private final double HEIGHT;
    private Label title;
    private HBox topRightBox;

    public BaseStage(String fxml, double width, double height) {
        WIDTH = width;
        HEIGHT = height;

        var root = AssetLoader.loadLayout(fxml);

        var topBox = (HBox) root.lookup("#topBox");
        var topLeftBox = (HBox) topBox.lookup("#topLeftBox");
        topRightBox = (HBox) topBox.lookup("#topRightBox");

        topLeftBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.3));
        topRightBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.7));
        title = (Label) topLeftBox.lookup("#title");
        scene = new Scene(root);
        setScene(scene);
        setTitle("畅聊");
        initStyle(StageStyle.UNDECORATED);
        getIcons().add(AssetLoader.loadAssetImage("images/icon.png", 200, 200));
        setWidth(WIDTH);
        setHeight(HEIGHT);
        registerWindowEvent(topBox);
        show();

    }

    /**
     * 管理窗口状态
     * pr:最小化、最大化、关闭
     *
     * @param topBox
     */
    private void registerWindowEvent(HBox topBox) {

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
                close.setOnAction(e -> Platform.exit());
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

    /**
     * 设置窗口标题和样式
     *
     * @param t
     * @param styles
     */
    protected void setStageTitle(String t, String... styles) {
        Optional.ofNullable(styles).ifPresent(e -> Arrays.stream(e).forEach(title::setStyle));
        setStageTitle(t);
    }

    protected void setStageTitle(String t) {
        title.setText(t);
        this.setTitle(t);
    }

    /**
     * 移除窗口部件
     * pr:最小化、最大化、关闭
     */
    protected void removeAction(StageComponet component) {
        switch (component) {
            case MINIMIZE:
                topRightBox.getChildren().remove(0);
                break;
            case MAXIMIZATION:
                topRightBox.getChildren().remove(1);
                break;
            case CLOSE:
                topRightBox.getChildren().remove(2);
                break;
            default:
        }

    }

}
