package cn.navigation.education.changliao.base;

import javafx.scene.control.Control;

/**
 * 自定义组件
 */

public abstract class NavigatorFXControl extends Control {
    private String styleSheet;

    protected final String getUserAgentStyleSheet(Class<?> clazz,String fileName){
        if (styleSheet==null){
            this.styleSheet = clazz.getResource(fileName).toExternalForm();
        }
        return this.styleSheet;
    }
}
