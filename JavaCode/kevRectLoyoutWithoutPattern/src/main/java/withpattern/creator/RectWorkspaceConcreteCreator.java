package withpattern.creator;

import withpattern.product.RectWorkspace;


public class RectWorkspaceConcreteCreator extends RectWorkspaceCreator {
    @Override
    public RectWorkspace getRectangle() {
        return new RectWorkspace();
    }
}
