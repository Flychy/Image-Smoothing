package Package2;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;




public abstract class ImageSmooth extends ImageComb {


    private long timeS;
    private BufferedImage imageS;


    //constructor that inherited the method ImageSmooth
    public ImageSmooth ()
    {
        this.imageS = super.getImage();
    }

    //image processing
    public void smooth() {

        timeS = System.currentTimeMillis(); //var used to take the right amount of time
        //the kernel
        Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
        //convolution matrix created using the function convolveOp
        BufferedImageOp op = new ConvolveOp(kernel);
        //now we are going to modify the image by using the matrix convolution and the information form the image
        this.imageS = op.filter(super.getImage(), null);
        timeS = System.currentTimeMillis() - timeS;  //calculates the processing time
    }

    //return the edited image
    public BufferedImage getImageS() {
        return this.imageS;
    }

    //processing time
    @Override
    public long smoothImageTime(){
        System.out.println("Timp prelucrare: " + timeS / 1000.0f + "s");
        return timeS;
    }

    public long writeTime(){
        return 0;
    }


}