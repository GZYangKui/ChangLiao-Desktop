package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.MainContentBase;
import cn.navigation.education.changliao.controller.MainPageController;
import cn.navigation.education.changliao.tool.AssetLoader;
import io.vertx.core.json.JsonObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;
import static cn.navigation.education.changliao.config.Constant.NICKNAME;


public class MailListItem {
    private HBox box = new HBox();
    //昵称
    private Label nickName = new Label();
    //头像
    private ImageView icon = new ImageView();

    public MailListItem(JsonObject user) {

        this.nickName.setText(user.getString(NICKNAME));

        this.nickName.setStyle("-fx-font-size:16px");
        icon.setImage(AssetLoader.loadAssetImage("images/header.jpg", 30, 30));
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        box.getChildren().addAll(icon,nickName);
        box.setOnMouseClicked(e->{
            MainPageController controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
            MainContentBase contentBase = new MailDetail(user);
            controller.setContent(contentBase);
        });
    }

    /**
     * 获取HBox对象
     * @return
     */
    public HBox getContent(){
        return box;
    }
}
