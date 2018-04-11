package withpattern.tools.lowline;//排序算法需要使用到的工具类

import lombok.Data;

@Data
public class LowLine implements Comparable<LowLine> {
    int x1;//水平线的左横坐标
    int x2;//水平线的右横坐标
    int y;//水平线的高度
    int left_y;//当前水平线的左边那一条水平线的高度
    int right_y;//当前水平线的右边那一条水平线的高度
    int width;//水平线宽度（x2-x1）

    //重新计算当前的宽度
    public int setWidth() {
        return this.width = this.x2 - this.x1;
    }

    //和左边的水平线互相更新，若等高则吞并为一条
    public void merge_left(LowLine left_l) {
        if (this.y == left_l.y) {
            this.x1 = left_l.x1;
            this.left_y = left_l.left_y;
            this.setWidth();
            left_l.x2 = left_l.x1;
            left_l.setWidth();
        } else {
            this.left_y = left_l.y;
            left_l.right_y = this.y;
        }
    }

    //和右边的水平线互相更新，若等高则吞并为一条
    public void merge_right(LowLine right_l) {
        if (this.y == right_l.y) {
            this.x2 = right_l.x2;
            this.right_y = right_l.right_y;
            this.setWidth();
            right_l.x1 = right_l.x2;
            right_l.setWidth();
        } else {
            this.right_y = right_l.y;
            right_l.left_y = this.y;
        }
    }

    //当前水平线无法放下任何一个矩形件的时候，提升高度并和左端或者右端合并
    public void updateY(LowLine leftLine, LowLine rightLine) {
        if (this.left_y <= this.right_y) {
            this.y = this.left_y;
        } else {
            this.y = this.right_y;
        }
        this.merge_left(leftLine);
        this.merge_right(rightLine);
    }

    //bool operator<(lowline o);//C++代码中用于指定排序的标准,Java这里不需要，可以在调用时再指定或者实现comparable接口
    //排序时再指定排序标准会比较灵活，但是这里不会有第二个排序标准，即时需要指定新的排序仍然可以排序时再指定
    public int compareTo(LowLine o) {
        return this.x1 - o.x1;
    }

    public LowLine() {

    }

    public LowLine(int x1, int x2, int y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
        this.left_y = -1;
        this.right_y = -1;
        this.setWidth();
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLeft_y() {
        return left_y;
    }

    public void setLeft_y(int left_y) {
        this.left_y = left_y;
    }

    public int getRight_y() {
        return right_y;
    }

    public void setRight_y(int right_y) {
        this.right_y = right_y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
