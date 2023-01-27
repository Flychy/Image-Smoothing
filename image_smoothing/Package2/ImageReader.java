package Package2;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

public abstract class ImageReader extends Dimensional{
    //Implementation of getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return img;
    }

    //Declaring constructors  with parameters, without parameters and without image within the buffer
    ImageReader(){
        super();
    }

    ImageReader(int WIDTH, int HEIGHT, BufferedImage image){
        height = HEIGHT;
        width = WIDTH;
        img = image;
    }

    ImageReader(int WIDTH, int HEIGHT, int section){
        width = WIDTH;
        height = HEIGHT;
        img = new BufferedImage(WIDTH, HEIGHT, section);
    }

    //As we want some keyboard input we are doing a method where we can read and find
    //the path of the image we want to edit, returning the name of the file we are
    //working with

    String imagePath()
    {
        String imgPath = null;
        long timeWasted1 = System.currentTimeMillis();

        System.out.println("The path is : " + " (BMP) : ");
        Scanner scanner = new Scanner(System.in);
        String imagePath = scanner.nextLine();

        System.out.println("Time elapsed to read image : " +timeWasted1+ " ms");
        return imagePath();
    }

    //Now, we are doing the actual read from the file
    public void read (String imageName)
    {
        long wastedTime1 = System.currentTimeMillis();
        try
        {
            File input = new File(imageName);
            img = ImageIO.read(input);
            width = img.getWidth();
            height = img.getHeight();
        }

        catch (IOException e)
        {
            System.out.println("Error");
            System.exit(0);
        }

        long wastedTime2 = System.currentTimeMillis();
        long wastedTime3 = wastedTime2 - wastedTime1;
        System.out.println("The reading of image have been done successfully!");
        System.out.println("Time elapsed to read the image : " +wastedTime3+ " ms");

    }

    //processing time
    public abstract long smoothImageTime();
}
