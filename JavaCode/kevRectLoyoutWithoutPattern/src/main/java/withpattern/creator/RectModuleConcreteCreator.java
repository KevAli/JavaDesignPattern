package withpattern.creator;

import withpattern.product.RectModule;
import withpattern.product.Rectangle;

public class RectModuleConcreteCreator extends RectModuleCreator {

    @Override
    public RectModule getRectangle() {
        return new RectModule();
    }
}
