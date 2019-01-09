package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.model.Position;
import cn.navigation.education.changliao.skin.PopStarSkin;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;


public class PopStar extends PopupControl {
    public PopStar(Position position) {
        setAutoFix(true);
        setX(position.getX()-40);
        setY(position.getY()-40);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new PopStarSkin(this);
    }
}
