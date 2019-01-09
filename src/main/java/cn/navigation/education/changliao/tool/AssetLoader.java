package cn.navigation.education.changliao.tool;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.logging.Logger;

public class AssetLoader {
    /**
     * 加载图片资源,指定图片大小
     *
     * @param fileName
     * @return
     */
    public static Image loadAssetImage(String fileName, double width, double height) {
        var image = new Image(ClassLoader.getSystemResourceAsStream("assets/" + fileName), width, height, false, true);
        return image;
    }

    /**
     * 加载图片，不指定图片大小，原图加载
     *
     * @param fileName
     * @return
     */
    public static Image loadAssetImage(String fileName) {
        return new Image(ClassLoader.getSystemResourceAsStream("assets/" + fileName));
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
