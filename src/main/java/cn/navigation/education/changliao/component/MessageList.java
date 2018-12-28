package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.BaseLeftContent;

import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

import static cn.navigation.education.changliao.config.Constant.BODY;
import static cn.navigation.education.changliao.config.Constant.FROM;

/**
 * 消息列表
 */
public class MessageList extends BaseLeftContent {

    private List<MessageListItem> items = new ArrayList<>();

    public MessageList() { }


    @Override
    public BorderPane getContent() {
        return container;
    }

    @Override
    public void updateUi(JsonObject d) {
        var nickName = d.getString(FROM);
        var body = d.getString(BODY);
        //遍历是该消息条目是否存在，如果存在更新为最新消息
        for (MessageListItem i : items) {

            if (i.getId().equals(nickName)) {
                i.updateMsg(body);
                return;
            }
        }

        //如果不存在，新建消息条目
        var item = new MessageListItem(nickName, body);
        items.add(item);
        Platform.runLater(() ->
                messageList.getItems().add(item.getMessageListItem())
        );

    }
}
