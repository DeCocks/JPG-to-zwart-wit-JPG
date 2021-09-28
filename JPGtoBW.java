package com.decocks;

// Java program to demonstrate get and set pixel
// values of an image
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class JPGtoBW
{
    public static void main(String args[])throws IOException
    {
        BufferedImage img = null;
        File file = null;

        //read image
        file = new File("C:\\Users\\Admin\\Documents\\ocrTess\\TestFoto.jpg");
        img = ImageIO.read(file);

        //threshold zoeken van foto
        int rTot=0;
        int gTot=0;
        int bTot=0;
        int br = img.getWidth();//get image width and height
        int ho = img.getHeight();
        for(int tel1 = 0; tel1 < 64; tel1++) {  //kies 64 pixels om te berekenen
            int br1 = (int)(Math.random()*br);  // Generates random integers 0 to breedte of hoogte van foto
            int ho1 = (int)(Math.random()*ho);
            int p = img.getRGB(br1, ho1);//get pixel value van deze pixel
            int r = (p >> 16) & 0xff;//get red
            int g = (p >> 8) & 0xff;//get green
            int b = p & 0xff;       //get blue
            //int a = (p>>24) & 0xff;//get alpha
            rTot+=r;    //totaal rood groen blauw
            gTot+=g;
            bTot+=b;
        }
        int thres =(rTot+gTot+bTot)/(3*64);
        System.out.println("thresold="+thres);

        //naar zwart wit zetten
        for(int ho1 = 0; ho1 < ho; ho1++) {
            for(int br1 = 0; br1 < br; br1++) {
               //get pixelwaarde
                int p = img.getRGB(br1, ho1);//get pixel value van deze pixel
                int r = (p >> 16) & 0xff;//get red
                int g = (p >> 8) & 0xff;//get green
                int b = p & 0xff;       //get blue
                // test op threshold
                if(r+g+b<3*thres){      //verander pixel in wit
                    r=0;
                    g=0;
                    b=0;
                    p = (255<<24) | (r<<16) | (g<<8) | b;
                    img.setRGB(br1, ho1, p);
                }else {
                    r=255;
                    g=255;
                    b=255;
                    p = (255<<24) | (r<<16) | (g<<8) | b;
                    img.setRGB(br1, ho1, p);
                }

            }
        }


        //write image
        try
        {
            File f = new File("C:\\Users\\Admin\\Documents\\ocrTess\\BWOut.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}

