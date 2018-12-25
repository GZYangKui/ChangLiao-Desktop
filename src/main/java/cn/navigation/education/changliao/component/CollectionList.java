package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.base.BaseLeftContent;
import javafx.scene.layout.BorderPane;

/**
 * 收藏列表
 */
public class CollectionList extends BaseLeftContent {

    @Override
    public BorderPane getContent() {
        return container;
    }

    @Override
    protected void customUi() {
        topBox.getChildren().remove(1);
        input.prefWidthProperty().bind(container.widthProperty().multiply(0.8));
    }
}
