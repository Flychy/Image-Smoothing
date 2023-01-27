package Package2;
import java.awt.image.BufferedImage;
//Syncronysing buffer class
public class ImageBuffer extends ImageReader {
    public BufferedImage image ;
    public ImageBuffer(int WIDTH, int HEIGHT, int section){
        super(WIDTH, HEIGHT, section);
    }

    @Override
    public long smoothImageTime() {
        return 0;
    }

    public ImageBuffer(){
        super();
    }
    private boolean available = false;
    public synchronized BufferedImage get(BufferedImage image, int x, int y) throws InterruptedException {
        while (!available) {
            if (Thread.currentThread().isInterrupted()){
                throw new InterruptedException();
            }
            wait();
        }
        available = false;
        notifyAll ();
        return image;
    }
    public synchronized void put (BufferedImage image, int x, int y) throws InterruptedException {
        while (available) {
            if (Thread.currentThread().isInterrupted()){
                throw new InterruptedException();
            }
            wait();
        }
        this.image = image;
        available = true;
        notifyAll();
    }
}