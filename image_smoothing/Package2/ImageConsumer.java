package Package2;
import java.awt.image.BufferedImage;

public class ImageConsumer extends Thread {
    private ImageBuffer bufferImage;
    private BufferedImage outImage;

    public ImageConsumer(ImageBuffer bufferImage){
        this.bufferImage = bufferImage;
        outImage = new BufferedImage(bufferImage.getWidth(), bufferImage.getHeight()
                , bufferImage.getImage().getType());
    }

    public void run(){
        try {//process every segment of the image
            int imageWidth = bufferImage.getWidth();
            int imageHeight = bufferImage.getHeight();
            int imageSize = imageWidth * imageHeight;
            int imageQuarter = imageSize / 4;
            for (int i = 0; i < imageSize; i++) {
                int x = i % imageWidth;
                int y = i / imageWidth;
                outImage = bufferImage.get(outImage, x, y);
                if(i % imageQuarter == 0) {
                    System.out.println("Consumer received 1/4 of the image to the buffer");
                    Thread.sleep(1000);
                }
            }
            System.out.println("Consumer received the entire image to buffer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
