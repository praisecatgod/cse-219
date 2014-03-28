/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgame.sprites;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Vector;

/**
 *
 * @author yv
 */
public class RegionKey {
    
    private String name;
    private Vector<Integer> xPixels;
    private Vector<Integer> yPixels;
    private Color color;
    boolean incorrect;
    private int[] value;
    
    public RegionKey(String initName, Color initColor)
    {
        name = initName;
        color = initColor;
        incorrect = false;
        value = getValue(color);
        xPixels = new Vector<Integer>();
        yPixels = new Vector<Integer>();
    }
    
    private int[] getValue(Color initColor)
    {
         int[] aValue = new int[3];
         aValue[0] = initColor.getRed();
         aValue[1] = initColor.getGreen();
         aValue[2] = initColor.getBlue(); 
         return aValue;
    }
    
    public void setPixels(BufferedImage img)
    {
                WritableRaster raster = img.getRaster();


        int[] dummy = null;
        for (int i = 0; i < raster.getWidth(); i++) {
            for (int j = 0; j < raster.getHeight(); j++) {
                int[] pixel = raster.getPixel(i, j, dummy);
                if ((pixel[0] == color.getRed())
                        && (pixel[1] == color.getGreen())
                        && (pixel[2] == color.getBlue())) 
                {
                    xPixels.add(i);;
                    yPixels.add(j);
                    
                }
            }
        }
        
       
    }
    
     public Color getColor()
        {
            return color;
        }
     
     public String getName()
     {
         return name;
     }
     
     public Vector<Integer> getXPixels()
     {
         return xPixels;
     }
     
     public Vector<Integer> getYPixels()
     {
         return yPixels;
     }
     
     public void canCorrect(BufferedImage img)
     {
         if(incorrect)
             revertPixels(img);
     }
     
     public void revertPixels(BufferedImage img)
     {

         WritableRaster raster = img.getRaster();
         int[] dummy = null;
         for(int i=0;i<xPixels.size();i++)
         {
            int[] pixel = raster.getPixel(xPixels.get(i), yPixels.get(i), dummy);
            pixel[0] = color.getRed();
            pixel[1] = color.getGreen();
            pixel[2] = color.getBlue();
            //pixel[3] = color.getTransparency();
            raster.setPixel(xPixels.get(i), yPixels.get(i), pixel);

         }
        incorrect = false;
     }
     
     public void redPixels(BufferedImage img)
     {

         WritableRaster raster = img.getRaster();
         int[] dummy = null;
         for(int i=0;i<xPixels.size();i++)
         {
            int[] pixel = raster.getPixel(xPixels.get(i), yPixels.get(i), dummy);
            pixel[0] = 250;
            pixel[1] = 0;
            pixel[2] = 0;
            //pixel[3] = 100;
            raster.setPixel(xPixels.get(i), yPixels.get(i), pixel);

         }
         incorrect = true;
     }
     
     public void greenPixels(BufferedImage img)
     {

         WritableRaster raster = img.getRaster();
         int[] dummy = null;
         for(int i=0;i<xPixels.size();i++)
         {
            int[] pixel = raster.getPixel(xPixels.get(i), yPixels.get(i), dummy);
            pixel[0] = 0;
            pixel[1] = 250;
            pixel[2] = 0;
            //pixel[3] = 100;
            raster.setPixel(xPixels.get(i), yPixels.get(i), pixel);

         }
     }
}
