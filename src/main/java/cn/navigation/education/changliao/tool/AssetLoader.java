package cn.navigation.education.changliao.tool;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;

public class AssetLoader {
    /**
     * 加载图片资源
     *
     * @param fileName
     * @return
     */
    public static Image loadAssetImage(String fileName, int width, int height) {
        var image = new Image(ClassLoader.getSystemResourceAsStream("assets/" + fileName), width, height, false, true);
        return image;
    }

    /**
     * 加载布局文件
     */
    public static Parent loadLayout(String url) {
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
