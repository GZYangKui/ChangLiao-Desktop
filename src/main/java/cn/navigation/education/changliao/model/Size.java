package cn.navigation.education.changliao.model;

/**
 * 尺寸model
 */
public class Size {
    private double width;
    private double height;

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Size() {
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
