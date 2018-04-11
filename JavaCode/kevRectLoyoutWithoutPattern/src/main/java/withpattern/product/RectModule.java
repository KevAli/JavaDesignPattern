package withpattern.product;//用于存放主类的包

import lombok.Data;

@Data   //lombok插件的一个注解，自动生成getter和setter代码简化项目代码
public class RectModule extends Rectangle {
    private String name;//矩形件的名称或者型号
    private int height;//矩形件的高度
    private int width;//矩形件的宽度
    private int timelim;//矩形件的工期要求，多少时间内必须完成
    private int area;//矩形件的面积
    private int value;//矩形件的价值，用于估计和其他矩形件相比谁应该优先
    private int priority;//矩形件的优先级，
    private int x;//矩形件排列的横坐标
    private int y;//矩形件排列的纵坐标
    private double fitness;//矩形件的对于当前摆放位置的适应度
    private boolean transable;//矩形件是否可以转置的标记
    private boolean packed;//矩形件是否已经排列的标记

    public void trans() {
        int a = this.height;
        this.height = this.width;
        this.width = a;

    }

    public RectModule() {

    }

    public RectModule(String name, int height, int width, int timelim) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.timelim = timelim;
        this.priority = timelim;
        this.area = height * width;
        this.value = (height * width) * (timelim) / 1000;
        this.x = 0;
        this.y = 0;
        this.transable = true;
        this.packed = false;
    }

    public void setProperty(String name, int height, int width, int timelim) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.timelim = timelim;
        this.priority = timelim;
        this.area = height * width;
        this.value = (height * width) * (timelim) / 1000;
        this.x = 0;
        this.y = 0;
        this.transable = true;
        this.packed = false;
    }
    //bool operator<(RectModule other);//C++代码中重载比较操作符，自主设定排序的标准，java使用另一种方法可以在排序的时候再指定排序标准
    //static bool sortByFitness(list<RectModule>::iterator a1,list<RectModule>::iterator a2);//C++代码中用于另一种排序标准的函数入口，这里也不需要

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getTimelim() {
        return timelim;
    }

    public void setTimelim(int timelim) {
        this.timelim = timelim;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public boolean isTransable() {
        return transable;
    }

    public void setTransable(boolean transable) {
        this.transable = transable;
    }

    public boolean isPacked() {
        return packed;
    }

    public void setPacked(boolean packed) {
        this.packed = packed;
    }
}
