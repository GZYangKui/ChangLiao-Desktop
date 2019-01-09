package cn.navigation.education.changliao.pages;

import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.enums.StageComponet;

/**
 * 个人信息页面
 */
public class Individual extends BaseStage {
    public Individual() {
        super("fxml/individual.fxml",350,500);
        removeAction(StageComponet.MAXIMIZATION);
    }

}
