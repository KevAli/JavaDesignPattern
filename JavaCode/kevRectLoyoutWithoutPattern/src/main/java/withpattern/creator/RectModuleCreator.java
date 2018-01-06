package withpattern.creator;

import withpattern.product.RectModule;
import withpattern.product.Rectangle;

public class RectModuleCreator extends RectCreator {

    @Override
    public Rectangle getRectangle() {
        return new RectModule();
    }
}
