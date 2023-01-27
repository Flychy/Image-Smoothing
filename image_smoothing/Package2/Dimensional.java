package Package2;
import java.awt.image.BufferedImage;

public abstract class Dimensional implements DimensionalGet {
    protected int width;
    protected int height;
    protected BufferedImage img;
    public abstract int getHeight();
    public abstract int getWidth();
    public abstract BufferedImage getImage();
    Dimensional() {
        width = 0;
        height = 0;
    };


}