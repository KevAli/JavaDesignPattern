package withpattern.strategy.layout;

import withpattern.product.RectModule;
import withpattern.tools.lowline.LowLineList;
import withpattern.product.RectWorkspace;

import java.util.List;

public interface LayoutStrategy {
    public List<List<RectModule>> layout(int width,int height,List<RectModule> mdls, LowLineList lowLineList);
}
