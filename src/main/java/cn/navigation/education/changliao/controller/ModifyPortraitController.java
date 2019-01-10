package cn.navigation.education.changliao.controller;

import cn.navigation.education.changliao.base.BaseController;
import cn.navigation.education.changliao.tool.AssetLoader;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static cn.navigation.education.changliao.config.Constant.COUNTER_CLOCKWISE;

public class ModifyPortraitController extends BaseController implements Initializable {
    @FXML
    private HBox topBox;
    @FXML
    private JFXButton localUpload;
    @FXML
    private JFXButton selectedPortrait;
    @FXML
    private JFXButton upload;
    @FXML
    private JFXButton cancel;
    @FXML
    private HBox scaleBox;
    @FXML
    private HBox rotateBox;
    @FXML
    private Canvas canvas;
    @FXML
    private JFXButton enLarge;
    @FXML
    private JFXButton narrow;
    @FXML
    private Slider slider;
    @FXML
    private JFXButton counterClockwise;
    @FXML
    private JFXButton clockwise;

    private GraphicsContext gc;

    //初始化角度 0
    private double initAngle = 0.0;

    //初始化放大倍数
    private float initProportion = 1.0f;

    private final String TEST_PATH = "images/header.jpg";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        event();

    }

    private void initView() {
        scaleBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.7));
        rotateBox.prefWidthProperty().bind(topBox.widthProperty().multiply(0.3));
        gc = canvas.getGraphicsContext2D();
        drawBg(AssetLoader.loadAssetImage(TEST_PATH), canvas.getWidth(), canvas.getHeight());
    }

    private void drawBg(Image image, double width, double height) {
        //清空画布
        gc.clearRect(0.0D, 0.0D, canvas.getWidth(), canvas.getHeight());
        //绘制图片
        gc.drawImage(image, 0.0, 0.00, width, height);
        //设置画笔粗细
        gc.setLineWidth(1);
        gc.strokeOval(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

    }

    private void event() {
        cancel.setOnAction(e -> {
            var w = cancel.getScene().getWindow();
            if (Objects.nonNull(w)) {
                w.hide();
            }
        });
        enLarge.setOnAction(this::enLagerImage);
        narrow.setOnAction(this::narrowImage);
        clockwise.setOnAction(this::rotateImage);
        counterClockwise.setOnAction(this::rotateImage);
    }


    /**
     * 放大图片
     */
    private void enLagerImage(ActionEvent event) {
        setSliderValue(0.2);

        initProportion += 0.2;

        if (getRoundValue(initProportion) > 2) {
            initProportion = 2;
            return;
        }

        var image = AssetLoader.loadAssetImage(TEST_PATH);
        drawBg(image, canvas.getWidth() * initProportion, canvas.getHeight() * initProportion);

    }

    /**
     * 缩小图片
     */
    private void narrowImage(ActionEvent event) {
        setSliderValue(-0.2);
        initProportion -= 0.2;
        if (getRoundValue(initProportion) < 1) {
            initProportion = 1;
            return;
        }
        var image = AssetLoader.loadAssetImage(TEST_PATH);
        drawBg(image, canvas.getWidth() * initProportion, canvas.getHeight() * initProportion);
    }

    /**
     * 旋转图片
     */
    private void rotateImage(ActionEvent e) {
        if (initAngle == 360) {
            initAngle = 0;
        }
        initAngle += 90;
        var target = (JFXButton) e.getSource();
        //逆时针旋转
        if (target.getId().equals(COUNTER_CLOCKWISE)) {
            canvas.setRotate(360 - initAngle);
            return;
        }
        //顺时针旋转
        canvas.setRotate(initAngle);

    }

    /**
     *
     * 保留两位小数
     * @param x
     * @return
     *
     */
    private double getRoundValue(double x) {
        var bg = new BigDecimal(x);
        var newValue = bg.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        System.out.println(newValue);
        return newValue;
    }

    /**
     * 设置Slider的值，正值为向右移动，负值向左移动
     *
     * @param offset 偏移量
     */

    private void setSliderValue(double offset) {

        if (offset < 0 && slider.getValue() == 0 ||
                offset > 0 && slider.getValue() == 1) {
            return;
        }
        slider.setValue(slider.getValue() + offset);

    }
}
