package Package2;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PipedOutputStream;
public class PipeHandler extends Thread{
    private PipedOutputStream pipeOut;
    private int bufferSize;

    public PipeHandler(PipedOutputStream pipeOut, int bufferSize) {
        this.pipeOut = pipeOut;
        this.bufferSize = bufferSize;
    }

    public void run() {
                System.out.println("PipeHandler received buffer");
    }
}

