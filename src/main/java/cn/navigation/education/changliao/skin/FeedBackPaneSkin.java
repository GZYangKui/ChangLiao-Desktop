package cn.navigation.education.changliao.skin;

import cn.navigation.education.changliao.component.FeedBackPane;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class FeedBackPaneSkin implements Skin<FeedBackPane> {
    private FeedBackPane pane;
    private BorderPane container = new BorderPane();
    private TextArea textArea = new TextArea();
    private JFXButton sure = new JFXButton("确定");
    private JFXButton cancel = new JFXButton("取消");

    public FeedBackPaneSkin(FeedBackPane pane) {
        this.pane = pane;
        initView();
    }

    private void initView() {
        var a = new HBox();
        var b = new HBox();
        var title = new Label("意见反馈");
        title.setFont(Font.font(18));
        b.setAlignment(Pos.CENTER_RIGHT);
        b.setPadding(new Insets(10));
        a.setPadding(new Insets(10));
        b.setSpacing(10);
        a.getChildren().add(title);
        b.getChildren().addAll(sure, cancel);
        container.setTop(a);
        container.setCenter(textArea);
        container.setBottom(b);
        container.setStyle("-fx-background-color: #F5F5F5;-fx-padding: 10px 10px 10px 10px;-fx-pref-width: 400px;-fx-pref-height: 250px");
        sure.setStyle("-fx-background-color: #87D087;-fx-pref-width: 60px;-fx-pref-height: 25px;-fx-text-fill: white");
        cancel.setStyle("-fx-background-color: #FFFFFF;-fx-pref-width: 60px;-fx-pref-height: 25px");
        cancel.setOnAction(e -> pane.hide());

    }

    @Override
    public FeedBackPane getSkinnable() {
        return pane;
    }

    @Override
    public Node getNode() {
        return container;
    }

    @Override
    public void dispose() {
        pane = null;
    }
}
