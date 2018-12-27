package cn.navigation.education.changliao.behavior;

import cn.navigation.education.changliao.component.message.Bubble;
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.inputmap.InputMap;
import javafx.scene.input.MouseEvent;

public class BubbleBehavior extends BehaviorBase<Bubble> {

    private final InputMap<Bubble> bubbleInputMap = this.createInputMap();

    public BubbleBehavior(Bubble bubble) {
        super(bubble);
        event(bubble);

    }

    private void event(Bubble bubble) {
        bubble.setOnMouseClicked(this::mousePressed);
    }

    @Override
    public InputMap<Bubble> getInputMap() {
        return bubbleInputMap;
    }

    /**
     * 鼠标按下请求焦点
     *
     * @param var1
     */
    public void mousePressed(MouseEvent var1) {
        this.getNode().requestFocus();
    }
}
