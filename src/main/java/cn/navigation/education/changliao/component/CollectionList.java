package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.BaseLeftContent;
import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * 收藏列表
 */
public class CollectionList extends BaseLeftContent {


    @Override
    public BorderPane getContent() {
        return container;
    }

    @Override
    protected void customUi() {
        topBox.getChildren().remove(1);
        input.prefWidthProperty().bind(container.widthProperty().multiply(0.8));
    }

    @Override
    public void initData(Object data) {
        List<String> items = (List<String>) data;
        items.forEach(this::initListData);
    }

    private void initListData(String title) {
        Image image;
        switch (title) {
            case "链接":
                image = AssetLoader.loadAssetImage("images/link.png", 30, 30);

                break;
            case "相册":
                image = AssetLoader.loadAssetImage("images/photo.png", 30, 30);

                break;
            case "笔记":
                image = AssetLoader.loadAssetImage("images/note.png", 30, 30);

                break;
            case "文件":
                image = AssetLoader.loadAssetImage("images/file.png", 30, 30);

                break;
            case "音乐":
                image = AssetLoader.loadAssetImage("images/music.png", 30, 30);
                break;
            default:
                image = AssetLoader.loadAssetImage("images/whole_collect.png", 30, 30);
        }
        var icon = new ImageView(image);
        var titleLabel = new Label(title);
        HBox h = new HBox();
        h.setAlignment(Pos.CENTER_LEFT);
        h.setSpacing(10);
        h.getChildren().addAll(icon, titleLabel);
        messageList.getItems().add(h);
    }
}
