package cn.navigation.education.changliao.base;

import cn.navigation.education.changliao.enums.MessageSource;
import cn.navigation.education.changliao.enums.MessageType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 *
 * 消息基类 ，所有的消息都基于此类封装
 * @see cn.navigation.education.changliao.component.TextMessage (文本消息)
 *
 *
 */

public abstract class Message {
    //消息
    protected final Object msg;

    protected final HBox box = new HBox();

    protected final MessageType type;

    protected final MessageSource source;

    protected final ScrollPane scroll;

    /***
     *
     * @param msg 消息内容
     * @param source 消息来源
     * @param type 消息类型
     * @param scrollPane 父容器
     */

    public Message(Object msg, MessageSource source, MessageType type,ScrollPane scrollPane) {
        this.msg = msg;
        this.type = type;
        this.source = source;

        this.scroll = scrollPane;

    }
    public abstract void initView();

    public HBox getMessage(){
        return box;
    }
}
