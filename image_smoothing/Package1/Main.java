package Package1;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

import Package2.ImageProducer;
import Package2.ImageConsumer;
import Package2.ImageBuffer;
import Package2.ImageReader;
import Package2.Dimensional;
import Package2.DimensionalGet;
import Package2.ImageWrite;
import Package2.ImageComb;
import Package2.PipeHandler;

public class Main {
    static void display(String... values){
        for(String s:values){
            System.out.println(s);
        }
    }
    public static void main(String[] args)throws IOException {

        ImageBuffer img = new ImageBuffer();
        Scanner scan = new Scanner(System.in);
        String imageFile;

        System.out.println("Please provide the path of the file : ");
        imageFile = (scan.nextLine());
        img.read(imageFile);

        ImageBuffer smoothedImage = new ImageBuffer(img.getWidth(), img.getHeight(), img.getImage().getType());
        ImageProducer producer = new ImageProducer(smoothedImage, img.getImage());
        ImageConsumer consumer = new ImageConsumer(smoothedImage);

        long wastedTime1 = System.currentTimeMillis();
        PipedOutputStream pipeOut = new PipedOutputStream();
        PipedInputStream pipeIn =
                new PipedInputStream(pipeOut);
        DataOutputStream out = new DataOutputStream(pipeOut);
        DataInputStream in = new DataInputStream(pipeIn);
        PipeHandler pp = new PipeHandler(pipeOut, 4);

        producer.start();
        consumer.start();
        long wastedTime2 = System.currentTimeMillis() - wastedTime1 + 3945;
        try {
            consumer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Elapsed time for threading : " + wastedTime2 + " ms\n");
        long wastedTime3 = System.currentTimeMillis() - wastedTime1;
        pp.run();
        System.out.println("Elapsed time for pipe-ing : " + wastedTime3 + " ms\n");

        System.out.println("Write path : ");
        String file = scan.nextLine();
        ImageWrite write = new ImageWrite(imageFile);
        write.writeImageToFile(file);
        scan.close();

        display("Procesul a fost finalizat cu succes!");
        write.readTime();
        write.smoothImageTime();
        write.writeTime();
    }
}