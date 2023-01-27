package Package2;
import java.awt.image.BufferedImage;
//read image from input
public class ImageProducer extends Thread{
    private BufferedImage image;
    private ImageBuffer buffer;

    public ImageProducer(ImageBuffer buffer, BufferedImage image){
        this.buffer = buffer;
        this.image = image;
    }

    public void run(){
        try {//divide image into 4 segments
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int imageSize = imageWidth * imageHeight;
            int imageQuarter = imageSize / 4;
            for (int i = 0; i < imageSize; i++) {
                int x = i % imageWidth;
                int y = i / imageWidth;
                int rgb = image.getRGB(x, y);
                buffer.put(image, x, y);
                if(i % imageQuarter == 0) {
                    System.out.println("Producer added 1/4 of the image to the buffer");
                    Thread.sleep(1000);
                }
            }
            System.out.println("Producer added the entire image to buffer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
