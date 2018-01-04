package kev.Rectangle;



import kev.LowLine.LowLine;
import kev.LowLine.LowLineList;

import java.util.List;

public class RectWorkspace {
    private String name;//工作台的名字
    private int height;//工作台的高度
    private int width;//工作台宽度
    private List<RectModule> mdls;//未排序的矩形件列表
    private List<RectModule> mdsr;//当前一个工作台内可以排序好的矩形件列表
    private List<List<RectModule>> mdlsch;//矩形件排序方案，内含有多个mdsr排好的序列
    private LowLineList lowlinelist;//排序算法需要使用到的辅助工具类

    private void sortByValue() {

    }

    private void layoutWithLowLine() {

    }


    private void putModule(RectModule curModule, LowLine curLowLine) {

    }

    private int getMixHeight() {
        return 0;
    }

    RectWorkspace() {

    }

    private RectWorkspace(String name, int height, int width) {

    }

}
