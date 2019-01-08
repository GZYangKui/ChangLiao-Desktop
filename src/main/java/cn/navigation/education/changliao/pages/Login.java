package cn.navigation.education.changliao.pages;

import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.enums.StageCloseStrategy;

public class Login extends BaseStage {
    public Login() {
        super("fxml/login_view.fxml",300,400);
        setStageCloseStrategy(StageCloseStrategy.EXIT);
        centerOnScreen();
    }
}
