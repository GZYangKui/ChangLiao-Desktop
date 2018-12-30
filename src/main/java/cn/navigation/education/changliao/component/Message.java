package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.tool.AssetLoader;
import cn.navigation.education.changliao.enums.MessageType;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
        var text = new Text(msg.toString());
        text.setFont(Font.font(17f));
        box.setSpacing(10);
        var k = new HBox();
        //发起方
        if (source == MessageSource.FRIEND) {

            box.setAlignment(Pos.CENTER_LEFT);
            k.getChildren().addAll(header,text);
            //文字靠左显示
            text.setTextAlignment(TextAlignment.LEFT);

        } else {
            box.setAlignment(Pos.CENTER_RIGHT);
            //镜像显示文字
            text.setTranslateX(-1.0f);
            //文字靠右侧显示
            text.setTextAlignment(TextAlignment.RIGHT);
            //接收方
            k.getChildren().addAll(text, header);
        }
        k.setStyle("-fx-border-color: red;-fx-border-width: 1px;");
        box.getChildren().add(k);


    }


    public HBox getPane() {
        return box;
    }
}
