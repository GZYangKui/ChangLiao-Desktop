package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 消息列表
 */
public class MessageListItem {
    private HBox hBox = new HBox();
    private ImageView icon = new ImageView(AssetLoader.loadAssetImage("images/header.jpg", 30, 30));
    private VBox v = new VBox();
    private String id;
    private Text t = new Text();
    private String msg;
    private Text nickName = new Text();

    /**
     * @param id  用户id
     * @param msg 最新消息
     */
    public MessageListItem(String id, String msg) {
        this.id = id;
        this.msg = msg;
        initView();

    }

    private void initView() {
        t.setText(msg);
        nickName.setText(id);
        hBox.setSpacing(10);
        v.getChildren().addAll(nickName, t);
        hBox.getChildren().addAll(icon, v);
    }

    public String getId() {
        return id;
    }
    public void updateMsg(String msg){
        Platform.runLater(()->
            t.setText(msg)
        );
    }

    public HBox getMessageListItem() {
        return hBox;
    }
}
