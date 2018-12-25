package cn.navigation.education.changliao.tool;

import javafx.scene.image.Image;

public class AssetLoader {
    /**
     * 加载图片资源
     * @param fileName
     * @return
     */
    public static Image loadAssetImage(String fileName,int width,int height) {
        var image = new Image(ClassLoader.getSystemResourceAsStream("assets/"+fileName),width,height,false,true);
        return image;
    }
}
