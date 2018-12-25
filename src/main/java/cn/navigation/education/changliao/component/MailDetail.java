package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.MainContentBase;
import io.vertx.core.json.JsonObject;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static cn.navigation.education.changliao.config.Constant.ID;
import static cn.navigation.education.changliao.config.Constant.NICKNAME;

public class MailDetail extends MainContentBase {

    public MailDetail(JsonObject o) {
        super("fxml/items/mail_detail.fxml");
        Text nickName = (Text) container.lookup("#nickName");
        Text mark = (Text) container.lookup("#mark");
        Text area = (Text) container.lookup("#area");
        Text id = (Text) container.lookup("#id");
        Text source = (Text) container.lookup("#source");
        nickName.setText(nickName.getText()+" "+o.getString(NICKNAME));
        area.setText(area.getText()+"  中国");
        id.setText(id.getText()+o.getString(ID));
        source.setText(source.getText()+"  朋友推荐");
        mark.setText(mark.getText()+"  "+"无");
    }
}
