package Package2;


import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class ImageComb implements TimeInterface {

    private BufferedImage image;
    long time;

    //constructor
    public BufferedImage getImage() {
        return this.image;
    }

    //reads the image from the specified path
    public void readImageFromFile (String fileName) {

        File file = new File(fileName);
        time = System.currentTimeMillis(); //var used to take the current time

        //some exception management
        try {
            this.image = ImageIO.read(file);

        } catch (IOException e) {
            System.out.println("File not found!");
        }

        time = System.currentTimeMillis() - time; //read time calculation
    }

    //write time calculation
    @Override
    public long readTime() {
        System.out.println("Read time: " + time / 1000.0f + "s");
        return time;
    }

    //unimplemented method
    @Override
    public abstract long smoothImageTime();

    //unimplemented method
    @Override
    public abstract long writeTime();


}
