package cn.navigation.education.changliao.component.message;


import cn.navigation.education.changliao.base.NavigatorFXControl;
import cn.navigation.education.changliao.skin.BubbleSkin;
import javafx.scene.control.Skin;

public class Bubble extends NavigatorFXControl {
    private static final String DEFAULE_STYLE_CLASS = "bubble";
    public String text;

    public Bubble(String msg) {
        text = msg;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new BubbleSkin(this);
    }
}
