package kev.Rectangle;


import kev.LowLine.LowLine;
import kev.LowLine.LowLineList;
import lombok.Data;

import java.util.*;

@Data
public class RectWorkspace {
    private String name;//工作台的名字
    private int height;//工作台的高度
    private int width;//工作台宽度
    private List<RectModule> mdls = new ArrayList<RectModule>();//未排序的矩形件列表
    private List<RectModule> mdsr = new ArrayList<RectModule>();//当前一个工作台内可以排序好的矩形件列表
    private List<List<RectModule>> mdlsch = new ArrayList<List<RectModule>>();//矩形件排序方案，内含有多个mdsr排好的序列
    private LowLineList lowlinelist = new LowLineList();//排序算法需要使用到的辅助工具类

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

    public void layoutWithLowLine() {
        while (!this.mdls.isEmpty()) {
            int mixHeightOfModule = this.getMixHeight();
            Iterator<RectModule> curRectIter = this.mdls.iterator();
            while (curRectIter.hasNext()) {
                this.lowlinelist.del_0_wid_line();//获取最低水平线前先删除长度为0的水平线
                LowLine mixLowline = this.lowlinelist.getMixLowline();
                if (mixLowline.getY() == this.height) {
                    //当前板子已经排满了无法继续排列了，需要更换一块新板子
                    System.out.println("当前板子已经排满了无法继续排列了，需要更换一块新板子");
                    this.mdlsch.add(this.mdsr);
                    //this.mdsr.clear();
                    this.mdsr = new ArrayList<RectModule>();
                    this.lowlinelist.setGuard(this.width, this.height);
                    continue;
                }
                if (mixLowline.getWidth() < mixHeightOfModule)//如果最小的肯定放不下，更新最低水平线，跳出此次循环继续下一次循环
                {
                    System.out.println("如果最小的肯定放不下，更新最低水平线，跳出此次循环继续下一次循环");
                    mixLowline.updateY(this.lowlinelist.getByx2(mixLowline.getX1()), this.lowlinelist.getByx1(mixLowline.getX2()));
                    continue;
                }
                RectModule curRectModule = curRectIter.next();
                //如果横着可以放得下
                if (curRectModule.getWidth() <= mixLowline.getWidth() && curRectModule.getHeight() + mixLowline.getY() <= this.height) {
                    System.out.println("如果横着可以放得下");
                    this.putModule(curRectModule, mixLowline);
                    curRectIter.remove();
                    break;
                }
                //如果可以转置,且竖着可以放得下则转置后排放，否则什么也不做
                else if (curRectModule.isTransable() && curRectModule.getHeight() <= mixLowline.getWidth() &&
                        curRectModule.getWidth() + mixLowline.getY() <= this.height) {
                    System.out.println("如果可以转置,且竖着可以放得下则转置后排放，否则什么也不做");
                    curRectModule.trans();
                    this.putModule(curRectModule, mixLowline);
                    curRectIter.remove();
                    break;

                }
                //横竖都放不下
                else {
                    System.out.println("横竖都放不下");
                    if (!curRectIter.hasNext()) {
                        //任何一个都已经放不下了，更新最低水平线
                        System.out.println("任何一个都已经放不下了，更新最低水平线");
                        mixLowline.updateY(this.lowlinelist.getByx2(mixLowline.getX1()), this.lowlinelist.getByx1(mixLowline.getX2()));
                    }
                }
            }
        }
        if (!this.mdsr.isEmpty()) {
            this.mdlsch.add(this.mdsr);
            this.mdsr=new ArrayList<RectModule>();
            this.lowlinelist.setGuard(this.width, this.height);
        }
    }


    public void putModule(RectModule curModule, LowLine curLowLine) {
        curModule.setX(curLowLine.getX1());
        curModule.setY(curLowLine.getY());
        curModule.setPacked(true);
        LowLine newLowLine = new LowLine(curLowLine.getX1(), curLowLine.getX1() + curModule.getWidth(), curLowLine.getY() + curModule.getHeight());
        curLowLine.setX1(newLowLine.getX2());
        curLowLine.setWidth();
        //this.lowlinelist.del_0_wid_line();//每次都要删除一次效率低，getByX1的时候判断Width>0的才取
        newLowLine.merge_left(this.lowlinelist.getByx2(newLowLine.getX1()));
        newLowLine.merge_right(this.lowlinelist.getByx1(newLowLine.getX2()));
        this.lowlinelist.add_lowline(newLowLine);
        this.mdsr.add(curModule);
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
        if (height <= width) {
            this.width = width;
            this.height = height;
        } else {
            this.width = height;
            this.height = width;
        }
        this.lowlinelist.setGuard(this.width, this.height);
    }

}
