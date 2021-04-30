/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

/**
 *
 * @author BaoCôngIDOL
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;

public class DrawArea extends JPanel {

    // Image in which we're going to draw
    private BufferedImage bufimg;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates
    private int currentX, currentY, oldX, oldY;
    private int scalePoint=20;

    public DrawArea() {
        setDoubleBuffered(false);
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
//            public void mouseReleased(MouseEvent e) {
//                clear();
//            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // coord x,y when drag mouse
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    // draw line if g2 context not null
//                    g2.drawLine(oldX, oldY, currentX, currentY);
                    // refresh draw area to repaint
                    g2.fillOval(oldX-scalePoint, oldY-scalePoint, WIDTH+scalePoint, HEIGHT+scalePoint);
                    repaint();
                    // store current coords x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (bufimg == null) {
            bufimg = new BufferedImage(getSize().width, getSize().height, 
                BufferedImage.TYPE_BYTE_GRAY);        
            // image to draw null ==> we create
            g2 = (Graphics2D) bufimg.getGraphics();
            // enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }
        g.drawImage(bufimg, 0, 0, null);
    }

    // now we create exposed methods
    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }
    
    public byte[] getImageData(){
        byte[] pixels = ((DataBufferByte) bufimg.getRaster().getDataBuffer()).getData();
       
        for( int i = 0; i < bufimg.getHeight(); i++ ){
            for( int j = 0; j < bufimg.getWidth(); j++ ){
                try{
                    if (pixels[i*(bufimg.getWidth())+j]==0){
                        pixels[i*(bufimg.getWidth())+j]=1;
                    }else pixels[i*(bufimg.getWidth())+j]=0;
//                    System.out.print(pixels[i*(bufimg.getWidth())+j]+" ");
                }catch(Exception e){
                    System.out.println(e);
                }
            }
//            System.out.println();
        }
        
        return pixels;
    }
    
    public double[][] getMatrix(){
        double[][] st=new double[bufimg.getHeight()][bufimg.getWidth()];
        
        byte[] pixels = ((DataBufferByte) bufimg.getRaster().getDataBuffer()).getData();
       
        for( int i = 0; i < bufimg.getHeight(); i++ ){
            for( int j = 0; j < bufimg.getWidth(); j++ ){
                try{
                    if (pixels[i*(bufimg.getWidth())+j]==0){
                        pixels[i*(bufimg.getWidth())+j]=1;
                    }else pixels[i*(bufimg.getWidth())+j]=0;
                    st[i][j]=pixels[i*(bufimg.getWidth())+j];
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        } 
        
        double [][] ans=new double[28][28];
        matrixReduce(ans, st);
//        for (int i=0;i<28;i++){
//            for (int j=0;j<28;j++){
//                if (ans[i][j]>0) System.out.print('*'+" ");
//                else System.out.print('_'+" ");
//                
//            }
//            System.out.println("");
//        }
        return ans;
    }
    
    public static boolean matrixReduce(double[][] dst, double[][] src) {
        double dstMaxX = dst.length - 1, dstMaxY = dst[0].length - 1;
        double srcMaxX = src.length - 1, srcMaxY = src[0].length - 1;
        int count[][] = new int[dst.length][dst[0].length];

        for (int x = 0; x < src.length; x++) {
            for (int y = 0; y < src[0].length; y++) {
                int xx = (int) Math.round((double) x * dstMaxX / srcMaxX);
                int yy = (int) Math.round((double) y * dstMaxY / srcMaxY);

                dst[xx][yy] += src[x][y];
                count[xx][yy]++;
            }
        }

        for (int x = 0; x < dst.length; x++) {
            for (int y = 0; y < dst[0].length; y++) {
                dst[x][y] /= count[x][y];
            }
        }

        return true;
    }
}
