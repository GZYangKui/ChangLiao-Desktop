package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.enums.MessageType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class Message {
    //消息
    private final Object msg;
    private MessageType type;
    private HBox box;

    public Message(Object msg, MessageType type, MessageSource source, ScrollPane scroll) {
        this.msg = msg;
        this.type = type;
        if (type == MessageType.TEXT) {
            initView(source, scroll);
        }
    }

    public void initView(MessageSource source, ScrollPane scroll) {
        box = new HBox();
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
            k.getChildren().addAll(header,text);

        } else {
            box.setAlignment(Pos.CENTER_RIGHT);
            //镜像显示文字
            text.setTranslateX(-1.0f);
            //接收方
            k.getChildren().addAll(text, header);
        }
        k.maxWidthProperty().bind(scroll.widthProperty().multiply(0.6));
        k.setSpacing(10);

        text.setStyle(

                "-fx-border-width: 1px;" +
                "-fx-border-color: red;" +
                "-fx-border-radius: 5px;-fx-padding: 10px;" +
                "-fx-wrap-text: true;-fx-font-size: 17px;"
        );

        box.getChildren().add(k);


    }


    public HBox getPane() {
        return box;
    }
}
