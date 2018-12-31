package cn.navigation.education.changliao.utils;

import cn.navigation.education.changliao.listener.DragListener;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * 工具类
 *
 */
public class DragUtil {
    public static void addDragListener(Stage stage, Node root) {
        new DragListener(stage).enableDrag(root);
    }
}