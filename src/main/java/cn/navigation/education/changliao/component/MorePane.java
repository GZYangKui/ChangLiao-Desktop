package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.model.Position;
import cn.navigation.education.changliao.skin.MorePaneSkin;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;


public class MorePane extends PopupControl {
    public MorePane(Position p) {
        setAutoHide(true);
        setAutoFix(true);
        setX(p.getX()+35);
        setY(p.getY());
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new MorePaneSkin(this);
    }
}
