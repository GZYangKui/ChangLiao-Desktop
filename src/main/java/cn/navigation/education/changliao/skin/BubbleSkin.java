package cn.navigation.education.changliao.skin;

import cn.navigation.education.changliao.behavior.BubbleBehavior;
import cn.navigation.education.changliao.component.message.Bubble;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class BubbleSkin extends SkinBase<Bubble> {

    private final BubbleBehavior behavior;
    private final Rectangle rect;
    private final Bubble bubble;
    private StackPane pane;

    public BubbleSkin(Bubble bubble) {
        super(bubble);
        behavior = new BubbleBehavior(bubble);
        rect = new Rectangle();
        pane = new StackPane();
        pane.getChildren().add(rect);
        rect.setStrokeWidth(1);
        rect.setFill(Color.valueOf("#98E165"));
        rect.setWidth(50);
        rect.setHeight(50);
        rect.setSmooth(true);
        this.getChildren().addAll(pane);
        this.bubble = bubble;
        drawShape();

    }
    /**
     * 绘制边框
     */
    private void drawShape() {

    }
}
