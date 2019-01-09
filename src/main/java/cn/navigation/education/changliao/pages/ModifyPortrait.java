package cn.navigation.education.changliao.pages;

import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.enums.StageComponet;

/**
 *
 * 修改头像
 *
 */

public class ModifyPortrait extends BaseStage {
    public ModifyPortrait() {
        super("fxml/modify_portrait_view.fxml",386,618);
        removeAction(StageComponet.MAXIMIZATION);
        setStageTitle("更换头像","-fx-font-size:16px;");

    }

}
