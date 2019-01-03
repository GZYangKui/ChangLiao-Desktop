package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.BaseContent;
import cn.navigation.education.changliao.controller.MainPageController;
import com.jfoenix.controls.JFXButton;
import io.vertx.core.json.JsonObject;
import javafx.scene.text.Text;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;
import static cn.navigation.education.changliao.config.Constant.ID;
import static cn.navigation.education.changliao.config.Constant.NICKNAME;

public class MailDetail extends BaseContent {

    public MailDetail(JsonObject o) {
        super("fxml/items/mail_detail.fxml");
        initView(o);

    }

    public void initView(JsonObject o) {
        Text nickName = (Text) container.lookup("#nickName");
        Text mark = (Text) container.lookup("#mark");
        Text area = (Text) container.lookup("#area");
        Text id = (Text) container.lookup("#id");
        Text source = (Text) container.lookup("#source");
        JFXButton sendMessage = (JFXButton) container.lookup("#sendMessage");
        nickName.setText(nickName.getText() + " " + o.getString(NICKNAME));
        area.setText(area.getText() + "  中国");
        id.setText(id.getText() + "  " + o.getString(ID));
        source.setText(source.getText() + "  朋友推荐");
        mark.setText(mark.getText() + "  " + "无");
        //处理发送消息事件
        sendMessage.setOnAction(e -> {
            MainPageController controller = (MainPageController) CONTEXT.get(MainPageController.class.getName());
            controller.setContent(new ChatDialog(o.getString(ID)));
        });
    }
}
