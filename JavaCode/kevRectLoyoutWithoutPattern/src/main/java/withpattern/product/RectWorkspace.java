package withpattern.product;


import lombok.Data;
import withpattern.strategy.LayoutStrategy;
import withpattern.tools.lowline.LowLineList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public class RectWorkspace extends Rectangle {
    private String name;//工作台的名字
    private int height;//工作台的高度
    private int width;//工作台宽度
    private List<RectModule> mdls = new ArrayList<RectModule>();//未排序的矩形件列表
    private List<RectModule> mdsr = new ArrayList<RectModule>();//当前一个工作台内可以排序好的矩形件列表
    private List<List<RectModule>> mdlsch = new ArrayList<List<RectModule>>();//矩形件排序方案，内含有多个mdsr排好的序列
    private LowLineList lowlinelist = new LowLineList();//排序算法需要使用到的辅助工具类
    private LayoutStrategy layoutStrategy;

    public void sortByValue() {
        Collections.sort(this.mdls, new Comparator<RectModule>() {
            public int compare(RectModule o1, RectModule o2) {
                return o2.getValue() - o1.getValue();
            }
        });
    }

    public void sortByPriority() {
        Collections.sort(this.mdls, new Comparator<RectModule>() {
            public int compare(RectModule o1, RectModule o2) {
                return o2.getPriority() - o1.getPriority();
            }
        });
    }

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

        this.lowlinelist.setGuard(this.width, this.height);
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
        this.lowlinelist.setGuard(this.width, this.height);
    }

}
