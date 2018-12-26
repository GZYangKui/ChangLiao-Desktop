package cn.navigation.education.changliao.skin;

import cn.navigation.education.changliao.component.FeedBackPane;
import cn.navigation.education.changliao.component.MorePane;
import cn.navigation.education.changliao.pages.Backup;
import com.jfoenix.controls.JFXListView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.HBox;

public class MorePaneSkin implements Skin<MorePane> {
    private JFXListView moreItems = new JFXListView();
    private MorePane pane;

    public MorePaneSkin(MorePane pane) {
        this.pane = pane;
        initView();
    }

    private void initView() {
        var feedback = new Label("意见反馈");
        var backups = new Label("备份与恢复");
        var setting = new Label("设置");
        var a = new HBox();
        var b = new HBox();
        var c = new HBox();
        a.getChildren().add(feedback);
        b.getChildren().add(backups);
        c.getChildren().add(setting);
        moreItems.getItems().addAll(a, b, c);

        a.setOnMouseClicked(e -> {
            new FeedBackPane().show(pane.getOwnerWindow());
            pane.hide();
        });

        b.setOnMouseClicked(e -> {
            new Backup();
        });

        c.setOnMouseClicked(e -> {

        });
    }

    @Override
    public MorePane getSkinnable() {
        return pane;
    }

    @Override
    public Node getNode() {
        return moreItems;
    }

    @Override
    public void dispose() {
        moreItems.getItems().remove(0, moreItems.getItems().size());
        pane = null;
    }
}
