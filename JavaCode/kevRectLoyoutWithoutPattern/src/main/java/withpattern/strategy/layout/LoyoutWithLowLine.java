package withpattern.strategy.layout;

import withpattern.product.RectModule;
import withpattern.tools.lowline.LowLine;
import withpattern.tools.lowline.LowLineList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoyoutWithLowLine implements LayoutStrategy {
    public List<List<RectModule>> layout(int width, int height, List<RectModule> mdls, LowLineList lowLineList) {
        List<RectModule> mdsr = new ArrayList<RectModule>();
        List<List<RectModule>> mdlsch = new ArrayList<List<RectModule>>();
        while (!mdls.isEmpty()) {
            //int mixHeightOfModule = rectWorkspace.getMixHeight();

            Iterator<RectModule> curRectIter = mdls.iterator();
            while (curRectIter.hasNext()) {
                lowLineList.del_0_wid_line();//获取最低水平线前先删除长度为0的水平线
                LowLine mixLowline = lowLineList.getMixLowline();
                if (mixLowline.getY() == height) {
                    //当前板子已经排满了无法继续排列了，需要更换一块新板子
                    System.out.println("当前板子已经排满了无法继续排列了，需要更换一块新板子");
                    mdlsch.add(mdsr);
                    //rectWorkspace.mdsr.clear();
                    mdsr = new ArrayList<RectModule>();
                    lowLineList.setGuard(width, height);
                    continue;
                }
//                if (mixLowline.getWidth() < mixHeightOfModule)//如果最小的肯定放不下，更新最低水平线，跳出此次循环继续下一次循环
//                {
//                    System.out.println("如果最小的肯定放不下，更新最低水平线，跳出此次循环继续下一次循环");
//                    mixLowline.updateY(lowLineList.getByx2(mixLowline.getX1()),
//                            lowLineList.getByx1(mixLowline.getX2()));
//                    continue;
//                }
                RectModule curRectModule = curRectIter.next();
                //如果横着可以放得下
                if (curRectModule.getWidth() <= mixLowline.getWidth() && curRectModule.getHeight() + mixLowline.getY() <= height) {
                    System.out.println("如果横着可以放得下");
                    this.putModule(lowLineList, curRectModule, mixLowline, mdsr);
                    curRectIter.remove();
                    break;
                }
                //如果可以转置,且竖着可以放得下则转置后排放，否则什么也不做
                else if (curRectModule.isTransable() && curRectModule.getHeight() <= mixLowline.getWidth() &&
                        curRectModule.getWidth() + mixLowline.getY() <= height) {
                    System.out.println("如果可以转置,且竖着可以放得下则转置后排放，否则什么也不做");
                    curRectModule.trans();
                    this.putModule(lowLineList, curRectModule, mixLowline, mdsr);
                    curRectIter.remove();
                    break;

                }
                //横竖都放不下
                else {
                    System.out.println("横竖都放不下");
                    if (!curRectIter.hasNext()) {
                        //任何一个都已经放不下了，更新最低水平线
                        System.out.println("任何一个都已经放不下了，更新最低水平线");
                        mixLowline.updateY(lowLineList.getByx2(mixLowline.getX1()),
                                lowLineList.getByx1(mixLowline.getX2()));
                    }
                }
            }
        }
        if (!mdsr.isEmpty()) {
            mdlsch.add(mdsr);
            mdsr = new ArrayList<RectModule>();
            lowLineList.setGuard(width, height);
        }

        return mdlsch;
    }

    private void putModule(LowLineList lowLineList, RectModule curRectModule, LowLine mixLowline, List<RectModule> mdsr) {
        curRectModule.setX(mixLowline.getX1());
        curRectModule.setY(mixLowline.getY());
        curRectModule.setPacked(true);
        LowLine newLowLine = new LowLine(mixLowline.getX1(), mixLowline.getX1() + curRectModule.getWidth(), mixLowline.getY() + curRectModule.getHeight());
        mixLowline.setX1(newLowLine.getX2());
        mixLowline.setWidth();
        //this.lowlinelist.del_0_wid_line();//每次都要删除一次效率低，getByX1的时候判断Width>0的才取
        newLowLine.merge_left(lowLineList.getByx2(newLowLine.getX1()));
        newLowLine.merge_right(lowLineList.getByx1(newLowLine.getX2()));
        lowLineList.add_lowline(newLowLine);
        mdsr.add(curRectModule);
    }
}
