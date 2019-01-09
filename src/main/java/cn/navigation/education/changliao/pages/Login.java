package cn.navigation.education.changliao.pages;

import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.enums.StageCloseStrategy;
import cn.navigation.education.changliao.enums.StageComponet;

public class Login extends BaseStage {
    public Login() {
        super("fxml/login_view.fxml", 300, 400);
        removeAction(StageComponet.MAXIMIZATION);
        setStageCloseStrategy(StageCloseStrategy.EXIT);
        centerOnScreen();
        setStageTitle("登陆", "-fx-font-size:20px;");
    }
}
