package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.enums.MessageType;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Message {
    //消息
    private final Object msg;
    private MessageType type;
    private HBox box;

    public Message(Object msg, MessageType type, MessageSource source) {
        this.msg = msg;
        this.type = type;
        if (type == MessageType.TEXT) {
            initView(source);
        }
    }

    public void initView(MessageSource source) {
        box = new HBox();
        var image = AssetLoader.loadAssetImage("images/header.jpg", 50, 50);
        var header = new ImageView(image);
        var circle = new Circle();
        circle.setCenterX(image.getWidth() / 2);
        circle.setCenterY(image.getHeight() / 2);
        circle.setRadius(25);
        header.setClip(circle);
        var text = new Text(msg.toString());
        box.setSpacing(10);
        //发起方
        if (source == MessageSource.OWN) {
            box.setAlignment(Pos.CENTER_LEFT);
            box.getChildren().addAll(header, text);
        } else {
            box.setAlignment(Pos.CENTER_RIGHT);
            //旋转180度将文字对调
            text.setTranslateX(-1.0f);
            //接收方
            box.getChildren().addAll(text, header);
        }

    }

    public HBox getPane() {
        return box;
    }
}
