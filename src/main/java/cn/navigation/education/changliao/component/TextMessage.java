package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.Message;
import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.enums.MessageType;
import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class TextMessage extends Message {

    public TextMessage(Object msg, MessageType type, MessageSource source, ScrollPane scroll) {
        super(msg, source, type, scroll);
        initView();
    }

    @Override
    public void initView() {
        var image = AssetLoader.loadAssetImage("images/header.jpg", 50, 50);
        var header = new ImageView(image);
        var circle = new Circle();
        circle.setCenterX(image.getWidth() / 2);
        circle.setCenterY(image.getHeight() / 2);
        circle.setRadius(25);
        header.setClip(circle);
        var text = new Label(msg.toString());
        text.setLineSpacing(5);

        box.setSpacing(10);
        var k = new HBox();


        //发起方
        if (source == MessageSource.FRIEND) {

            box.setAlignment(Pos.CENTER_LEFT);
            k.getChildren().addAll(header, text);

        } else {
            box.setAlignment(Pos.CENTER_RIGHT);
            //镜像显示文字
            text.setTranslateX(-1.0f);
            //接收方
            k.getChildren().addAll(text, header);

        }

        text.setStyle(
                "-fx-padding: 10px;" +
                        "-fx-wrap-text: true;" +
                        "-fx-font-size: 17px;" +
                        "-fx-background-color:" + (source == MessageSource.FRIEND ? "#FFFFFF" : "#98E165")
        );

        k.maxWidthProperty().bind(scroll.widthProperty().multiply(0.6));
        k.setSpacing(10);

        box.getChildren().add(k);


    }
}
