package withpattern.strategy;

import withpattern.product.RectModule;
import withpattern.product.RectWorkspace;
import withpattern.tools.lowline.LowLine;
import withpattern.tools.lowline.LowLineList;

import java.util.ArrayList;
import java.util.Iterator;

public class LoyoutWithLowLine implements LayoutStrategy {

    @Override
    public void layout(RectWorkspace rectWorkspace, LowLineList lowLineList) {
        while (!rectWorkspace.getMdls().isEmpty()) {
            int mixHeightOfModule = rectWorkspace.getMixHeight();
            Iterator<RectModule> curRectIter = rectWorkspace.getMdls().iterator();
            while (curRectIter.hasNext()) {
                rectWorkspace.getLowlinelist().del_0_wid_line();//获取最低水平线前先删除长度为0的水平线
                LowLine mixLowline = rectWorkspace.getLowlinelist().getMixLowline();
                if (mixLowline.getY() == rectWorkspace.getHeight()) {
                    //当前板子已经排满了无法继续排列了，需要更换一块新板子
                    System.out.println("当前板子已经排满了无法继续排列了，需要更换一块新板子");
                    rectWorkspace.getMdlsch().add(rectWorkspace.getMdsr());
                    //rectWorkspace.mdsr.clear();
                    rectWorkspace.setMdsr(new ArrayList<RectModule>());
                    rectWorkspace.getLowlinelist().setGuard(rectWorkspace.getWidth(), rectWorkspace.getHeight());
                    continue;
                }
                if (mixLowline.getWidth() < mixHeightOfModule)//如果最小的肯定放不下，更新最低水平线，跳出此次循环继续下一次循环
                {
                    System.out.println("如果最小的肯定放不下，更新最低水平线，跳出此次循环继续下一次循环");
                    mixLowline.updateY(rectWorkspace.getLowlinelist().getByx2(mixLowline.getX1()),
                            rectWorkspace.getLowlinelist().getByx1(mixLowline.getX2()));
                    continue;
                }
                RectModule curRectModule = curRectIter.next();
                //如果横着可以放得下
                if (curRectModule.getWidth() <= mixLowline.getWidth() && curRectModule.getHeight() + mixLowline.getY() <= rectWorkspace.getHeight()) {
                    System.out.println("如果横着可以放得下");
                    this.putModule(rectWorkspace,curRectModule, mixLowline);
                    curRectIter.remove();
                    break;
                }
                //如果可以转置,且竖着可以放得下则转置后排放，否则什么也不做
                else if (curRectModule.isTransable() && curRectModule.getHeight() <= mixLowline.getWidth() &&
                        curRectModule.getWidth() + mixLowline.getY() <= rectWorkspace.getHeight()) {
                    System.out.println("如果可以转置,且竖着可以放得下则转置后排放，否则什么也不做");
                    curRectModule.trans();
                    this.putModule(rectWorkspace,curRectModule, mixLowline);
                    curRectIter.remove();
                    break;

                }
                //横竖都放不下
                else {
                    System.out.println("横竖都放不下");
                    if (!curRectIter.hasNext()) {
                        //任何一个都已经放不下了，更新最低水平线
                        System.out.println("任何一个都已经放不下了，更新最低水平线");
                        mixLowline.updateY(rectWorkspace.getLowlinelist().getByx2(mixLowline.getX1()),
                                rectWorkspace.getLowlinelist().getByx1(mixLowline.getX2()));
                    }
                }
            }
        }
        if (!rectWorkspace.getMdsr().isEmpty()) {
            rectWorkspace.getMdlsch().add(rectWorkspace.getMdsr());
            rectWorkspace.setMdsr(new ArrayList<RectModule>());
            rectWorkspace.getLowlinelist().setGuard(rectWorkspace.getWidth(), rectWorkspace.getHeight());
        }


    }

    private void putModule(RectWorkspace rectWorkspace, RectModule curRectModule, LowLine mixLowline) {
        curRectModule.setX(mixLowline.getX1());
        curRectModule.setY(mixLowline.getY());
        curRectModule.setPacked(true);
        LowLine newLowLine = new LowLine(mixLowline.getX1(), mixLowline.getX1() + curRectModule.getWidth(), mixLowline.getY() + curRectModule.getHeight());
        mixLowline.setX1(newLowLine.getX2());
        mixLowline.setWidth();
        //this.lowlinelist.del_0_wid_line();//每次都要删除一次效率低，getByX1的时候判断Width>0的才取
        newLowLine.merge_left(rectWorkspace.getLowlinelist().getByx2(newLowLine.getX1()));
        newLowLine.merge_right(rectWorkspace.getLowlinelist().getByx1(newLowLine.getX2()));
        rectWorkspace.getLowlinelist().add_lowline(newLowLine);
        rectWorkspace.getMdsr().add(curRectModule);
    }
}
