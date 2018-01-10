package withpattern.product;


import lombok.Data;
import withpattern.strategy.layout.LayoutStrategy;
import withpattern.tools.lowline.LowLineList;

import java.util.ArrayList;
import java.util.List;

@Data
public class RectWorkspace extends Rectangle {
    private String name;//工作台的名字
    private int height;//工作台的高度
    private int width;//工作台宽度
    private List<RectModule> mdls = new ArrayList<RectModule>();//未排序的矩形件列表
    private LayoutStrategy layoutStrategy;
    private List<List<RectModule>> mdlsch = new ArrayList<List<RectModule>>();//矩形件排序方案，内含有多个mdsr排好的序列

    /**
     * 删除以下两个属性，目的是降低算法和工作台时间的耦合度
     */
    //private LowLineList lowlinelist = new LowLineList();//排序算法需要使用到的辅助工具类
    //private List<RectModule> mdsr = new ArrayList<RectModule>();//当前一个工作台内可以排序好的矩形件列表

    public int getMixHeight() {
        int mixHeight = this.mdls.get(0).getHeight();
        for (RectModule o : this.mdls) {
            if (o.getHeight() < mixHeight) {
                mixHeight = o.getHeight();
            }
        }
        return mixHeight;
    }

    public RectWorkspace() {

    }

    public RectWorkspace(String name, int height, int width) {
        this.name = name;
        if (height > width) {
            int temp = height;
            height = width;
            width = temp;
        }
        this.width = width;
        this.height = height;
    }

    public void setProperty(String name, int height, int width) {
        this.name = name;
        if (height > width) {
            int temp = height;
            height = width;
            width = temp;
        }
        this.width = width;
        this.height = height;
    }

}
