package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.controller.MainPageController;
import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;

/**
 * 消息列表
 */
public class MessageListItem {
    private HBox hBox = new HBox();
    private ImageView icon = new ImageView(AssetLoader.loadAssetImage("images/header.jpg", 30, 30));
    private VBox v = new VBox();
    //用户id
    private String id;
    //显示消息
    private Label t = new Label();
    //消息内容
    private String msg;
    //用户昵称
    private Label nickName = new Label();

    private HBox h = new HBox();

    private Label futureNumber = new Label();

    private int number = 0;

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
        nickName.setFont(Font.font(17f));
        t.setFont(Font.font(15f));
        t.setTextOverrun(OverrunStyle.ELLIPSIS);

        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);

        v.getChildren().addAll(nickName, t);

        hBox.getChildren().addAll(icon, v, h);

        h.setAlignment(Pos.CENTER_RIGHT);
        h.getChildren().add(futureNumber);
        futureNumber.setStyle(
                "-fx-border-width: 1px;" +
                        "-fx-border-color: red;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-padding: 5px;"
        );
        futureNumber.setVisible(false);

        t.prefWidthProperty().bind(hBox.widthProperty().multiply(0.7));
        nickName.prefWidthProperty().bind(hBox.widthProperty().multiply(0.7));

        h.prefWidthProperty().bind(hBox.widthProperty().multiply(0.2));
        v.prefWidthProperty().bind(hBox.widthProperty().multiply(0.5));


        hBox.setOnMouseClicked(e -> {
            MainPageController controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
            controller.setContent(new ChatDialog(id));
            //置空未读信息
            number = 0;
            futureNumber.setVisible(false);
        });
    }

    public String getId() {
        return id;
    }

    public MessageListItem addFutureNumber() {
        Platform.runLater(() -> {
            futureNumber.setText(String.valueOf(++number));

            if (!futureNumber.isVisible())
                futureNumber.setVisible(true);
        });
        return this;
    }

    public void updateMsg(String msg) {
        Platform.runLater(() ->
                t.setText(msg)
        );
    }

    public HBox getMessageListItem() {
        return hBox;
    }
}
