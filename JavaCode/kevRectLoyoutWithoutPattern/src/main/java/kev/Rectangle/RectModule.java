package kev.Rectangle;//用于存放主类的包

import lombok.Data;

@Data   //lombok插件的一个注解，自动生成getter和setter代码简化项目代码
public class RectModule {
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

    RectModule() {

    }

    RectModule(String name, int height, int width, int timelim) {
        this.name = name;
        this.height = width;
        this.width = height;
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
}
