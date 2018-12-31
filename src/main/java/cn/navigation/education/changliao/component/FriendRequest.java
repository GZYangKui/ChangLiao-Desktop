package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.BaseNotificationItem;
import cn.navigation.education.changliao.enums.NotificationCommand;

public class FriendRequest extends BaseNotificationItem {

    public FriendRequest() {
        super("小明","你好，交个朋友吧！", NotificationCommand.AGREE,NotificationCommand.REFUSE);
    }
}
