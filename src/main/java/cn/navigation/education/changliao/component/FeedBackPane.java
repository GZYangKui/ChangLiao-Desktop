package cn.navigation.education.changliao.component;

import cn.navigation.education.changliao.skin.FeedBackPaneSkin;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;

public class FeedBackPane extends PopupControl {
    public FeedBackPane() {
        setAutoHide(true);
        setAutoFix(true);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new FeedBackPaneSkin(this);
    }
}
