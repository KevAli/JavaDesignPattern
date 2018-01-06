package withpattern.strategy;

import withpattern.tools.lowline.LowLineList;
import withpattern.product.RectWorkspace;

public interface LayoutStrategy {
    public abstract void layout(RectWorkspace rectWorkspace, LowLineList lowLineList);
}
