package withpattern.tools.lowline;


import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class LowLineList {
    private List<LowLine> lowlinelist =new ArrayList<LowLine>() ;//水平线列表集合

    //添加新的水平线到集合中，其实可以省略这个方法改用getLowlinelis（）.add（o）
    public void add_lowline(LowLine o) {
        this.lowlinelist.add(o);
    }

    //根据水平线的左横坐标值排序
    public void sortByx1() {
        this.del_0_wid_line();
        Collections.sort(lowlinelist);
    }

    //删除宽度为0的坐标值
    public void del_0_wid_line() {
        for (Iterator<LowLine> iter = this.lowlinelist.iterator(); iter.hasNext(); ) {
            LowLine lowLine = iter.next();
            if (lowLine.setWidth() == 0) {
                iter.remove();
            }
        }

    }

    //初始化水平线集合，添加左右哨兵和高度为0的第一条水平线
    public void setGuard(int r_Guard_width, int MaxH) {
        this.lowlinelist.clear();
        LowLine leftGuard = new LowLine(-1, 0, MaxH);
        LowLine rightGuard = new LowLine(r_Guard_width, r_Guard_width + 1, MaxH);
        LowLine firstLowLine = new LowLine(0, r_Guard_width, 0);
        firstLowLine.merge_left(leftGuard);
        firstLowLine.merge_right(rightGuard);
        this.add_lowline(leftGuard);
        this.add_lowline(firstLowLine);
        this.add_lowline(rightGuard);
    }

    //获取最低水平线高度
    public int getMixY() {
        int mixY = this.lowlinelist.get(0).getY();
        for (LowLine o : this.lowlinelist) {
            if (o.getY() < mixY) {
                mixY = o.getY();
            }
        }
        return mixY;
    }

    //根据水平线的左横坐标获取对应水平线
    public LowLine getByx1(int x1) {
        LowLine lowLine = this.lowlinelist.get(0);
        for (LowLine o : this.lowlinelist) {
            if (o.getX1() == x1 && o.setWidth() > 0) {
                lowLine = o;
                break;
            }
        }
        return lowLine;
    }

    //根据水平线的右横坐标获取对应水平线
    public LowLine getByx2(int x2) {
        LowLine lowLine = this.lowlinelist.get(0);
        for (LowLine o : this.lowlinelist) {
            if (o.getX2() == x2 && o.setWidth() > 0) {
                lowLine = o;
                break;
            }
        }
        return lowLine;
    }

    //根据水平线的纵坐标获取对应水平线,有可能出现多条，返回x1小的那一条
    public LowLine getByY(int y) {
        List<LowLine> listMixLowLine = new ArrayList<LowLine>();
        for (LowLine o : this.lowlinelist) {
            if (o.getY() == y) {
                listMixLowLine.add(o);
            }
        }
//排序效率比遍历一次取结果的效率低
//        Collections.sort(listMixLowLine, new Comparator<lowline>() {
//            public int compare(lowline o1, lowline o2) {
//                return o1.x1-o2.x1;
//            }
//        });
        LowLine lowLine = listMixLowLine.get(0);
        for (LowLine o : listMixLowLine) {
            if (o.x1 < lowLine.x1) {
                lowLine = o;
            }
        }
        return lowLine;
    }

    //获取高度最低的水平线，有可能出现多条，返回x1小的那一条
    public LowLine getMixLowline() {
        return this.getByY(this.getMixY());
    }

    public LowLineList() {

    }

}
