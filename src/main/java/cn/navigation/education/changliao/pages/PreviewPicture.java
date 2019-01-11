package cn.navigation.education.changliao.pages;

import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.utils.DragUtil;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreviewPicture extends Stage {
    private BorderPane container = new BorderPane();
    private ImageView imageView = new ImageView();
    private JFXButton upload = new JFXButton("上传");

    public PreviewPicture(Image image) {
        initView(image);
        setResizable(false);
        setTitle("预览头像");

        //设置窗口透明
        initStyle(StageStyle.TRANSPARENT);
    }

    private void initView(Image image) {
        imageView.setImage(image);
        var close = new JFXButton("", new ImageView(AssetLoader.loadAssetImage("images/close.png", 20, 20)));
        var clip = new Circle();
        var bottomBox = new HBox();
        var topBox = new HBox();
        var padding = new Insets(10);

        topBox.setAlignment(Pos.CENTER_RIGHT);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        topBox.setPadding(padding);
        bottomBox.setPadding(padding);
        topBox.getChildren().add(close);
        bottomBox.getChildren().add(upload);

        upload.setStyle(
                "-fx-background-color: #0EABE6;" +
                        "-fx-text-fill: #FFFF;" +
                        "-fx-padding: 5px"
        );
        container.setStyle("-fx-background:#282828;");
        container.setOpacity(0.9);

        clip.setRadius((image.getWidth() / 2) - 1);
        clip.setCenterX(image.getWidth() / 2);
        clip.setCenterY(image.getHeight() / 2);

        imageView.setClip(clip);
        container.setTop(topBox);
        container.setCenter(imageView);
        container.setBottom(bottomBox);


        var scene = new Scene(container, 600, 500);
        setScene(scene);
        //设置窗口可拖动
        DragUtil.addDragListener(this, topBox);

        close.setOnAction(e->this.close());


    }
}
