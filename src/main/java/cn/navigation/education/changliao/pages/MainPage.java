package cn.navigation.education.changliao.pages;


import cn.navigation.education.changliao.base.BaseStage;
import cn.navigation.education.changliao.controller.MainPageController;
import cn.navigation.education.changliao.enums.StageCloseStrategy;
import io.vertx.core.json.JsonObject;

import static cn.navigation.education.changliao.base.BaseController.CONTEXT;

public class MainPage extends BaseStage {
    public MainPage(JsonObject data) {
        super("fxml/main_view.fxml",900,600);
        CONTEXT.get(MainPageController.class.getName()).initData(data);
        setStageCloseStrategy(StageCloseStrategy.EXIT);
    }
}
