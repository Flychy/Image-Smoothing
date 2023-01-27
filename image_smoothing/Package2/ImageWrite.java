package Package2;

import java.io.FileNotFoundException;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageWrite extends ImageSmooth {

    long timeW;
    private static BufferedImage imageW;

    //constructor that uses the read method from ImageReader
    public ImageWrite ()
    {
        ImageWrite.imageW = super.getImageS();
    }

    //getter to return the img
    public static BufferedImage getImageW() {
        return ImageWrite.imageW;
    }

    //setter to take and edit images from ReadImage and ProcessImage
    public ImageWrite(String fileName)
    {
        super.readImageFromFile(fileName);
        super.smooth();
        ImageWrite.imageW = super.getImageS();
    }

    //method to write image to file
    public void writeImageToFile(String fileName) throws FileNotFoundException, IOException {

        timeW = System.currentTimeMillis(); //var used to display the time
        FileOutputStream stream = new FileOutputStream(fileName); //write file to a path

        //exception handler
        try{
            ImageIO.write(ImageWrite.imageW, "BMP", stream);
        } catch (FileNotFoundException e) {
            System.out.println(fileName + "invalid");
            return;
        } catch (IOException e) {
            System.out.println("Writing error!");
            return ;
        }

        timeW = System.currentTimeMillis() - timeW; //write time calculation
    }

    //write time
    @Override
    public long writeTime(){
        System.out.println("Writing image: " + timeW / 1000.0f + "s");

        return timeW;
    }



}