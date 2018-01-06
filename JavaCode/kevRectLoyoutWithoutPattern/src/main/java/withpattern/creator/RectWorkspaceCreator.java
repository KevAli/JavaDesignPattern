package withpattern.creator;

import withpattern.product.RectWorkspace;
import withpattern.product.Rectangle;

public class RectWorkspaceCreator extends RectCreator {
    @Override
    public Rectangle getRectangle() {
        return new RectWorkspace();
    }
}
