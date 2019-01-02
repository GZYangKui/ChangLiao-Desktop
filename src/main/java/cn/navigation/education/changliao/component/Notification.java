package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.controller.MainPageController;
import cn.navigation.education.changliao.tool.AssetLoader;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;

public class Notification {


    private HBox hBox = new HBox();
    private HBox a = new HBox();
    private HBox b = new HBox();
    //当前未读消息
    private int number = 0;
    private Label numberLabel = new Label();

    public Notification() {
        var image = AssetLoader.loadAssetImage("images/notification.png", 30, 30);
        var icon = new ImageView(image);
        var title = new Label("系统通知");
        numberLabel.setVisible(false);
        a.setSpacing(10);
        a.setAlignment(Pos.CENTER_LEFT);
        a.getChildren().addAll(icon, title);
        b.setAlignment(Pos.CENTER_RIGHT);
        b.getChildren().add(numberLabel);
        numberLabel.setStyle(
                        "-fx-border-width: 1px;" +
                        "-fx-border-color: red;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-padding: 5px;"
        );
        a.prefWidthProperty().bind(hBox.widthProperty().multiply(0.5));
        b.prefWidthProperty().bind(hBox.widthProperty().multiply(0.49));
        hBox.getChildren().addAll(a, b);

        hBox.setOnMouseClicked(e->{
            numberLabel.setVisible(false);
            //置空当前消息数目
            number = 0;

            MainPageController controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
            controller.setContent(new NotificationPane());
        });

    }

   public void addNotificationNumber(){
       Platform.runLater(()->{
           if (!numberLabel.isVisible()){
               numberLabel.setVisible(true);
           }
           numberLabel.setText(String.valueOf(++number));
       });

   }

    /**
     * 获取未读消息
     *
     * @return
     */
    public int getNumber() {
        return number;
    }

    public HBox getNotificationItem() {
        return hBox;
    }
}
