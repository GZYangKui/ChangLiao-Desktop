package cn.navigation.education.changliao.skin;

import cn.navigation.education.changliao.component.PopStar;
import cn.navigation.education.changliao.tool.AssetLoader;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PopStarSkin implements Skin<PopStar> {
    private final PopStar popStar;
    private final JFXButton star = new JFXButton();

    public PopStarSkin(PopStar popStar) {
        this.popStar = popStar;
        initView();
    }

    private void initView() {

        var path = "images/stars/star_" + (1 + new Random().nextInt(4)) + ".png";
        var image = AssetLoader.loadAssetImage(path, 40, 40);
        var imageView = new ImageView(image);
        star.setGraphic(imageView);
        final double initY = popStar.getY();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //向上移动100关闭定时器
                if (popStar.getY() <= initY - 100) {
                    this.cancel();
                    Platform.runLater(() -> popStar.hide());
                    return;
                }
                //每次向上移动10
                Platform.runLater(() -> popStar.setY(popStar.getY() - 30));
            }

        }, 0, 200);
    }

    @Override
    public PopStar getSkinnable() {
        return popStar;
    }

    @Override
    public Node getNode() {
        return star;
    }

    @Override
    public void dispose() {
    }
}
