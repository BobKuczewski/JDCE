package parts.input;
import java.awt.*;
import parts.*;
public class CPower extends parts.Component {
  public CPower() {
    super();
    pin=new int[41];
    }
  public int numPins() {
    return 2;
    }
  public int pinOut(int pn) {
    return 1;
    }
  public int run() {
    pin[1]=1;
    pin[2]=0;
    return 0;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int gx,gy;
    gx=(x-offsetX)*8;
    gy=(y-offsetY)*8;
    g.setColor(Color.white);
    g.drawOval(gx-2,gy-2,5,5);
    g.drawOval(gx-6,gy-6,13,13);
    g.drawLine(gx-13,gy,gx-9,gy);
    g.drawLine(gx-11,gy-2,gx-11,gy+2);
    gy+=24;
    g.drawOval(gx-2,gy-2,5,5);
    g.drawOval(gx-6,gy-6,13,13);
    g.drawLine(gx-13,gy,gx-9,gy);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    g.setColor(Color.lightGray);
    g.drawOval(ox-1,oy-1,3,3);
    g.drawOval(ox-2,oy-2,5,5);
    g.drawOval(ox-3,oy-3,7,7);
    g.drawOval(ox-4,oy-4,9,9);
    g.setColor(Color.red);
    g.drawOval(ox-5,oy-5,11,11);
    g.drawOval(ox-6,oy-6,13,13);
    g.drawOval(ox-7,oy-7,15,15);
    g.drawOval(ox-8,oy-8,17,17);
    oy-=24;
    g.setColor(Color.lightGray);
    g.drawOval(ox-1,oy-1,3,3);
    g.drawOval(ox-2,oy-2,5,5);
    g.drawOval(ox-3,oy-3,7,7);
    g.drawOval(ox-4,oy-4,9,9);
    g.setColor(Color.black);
    g.drawOval(ox-5,oy-5,11,11);
    g.drawOval(ox-6,oy-6,13,13);
    g.drawOval(ox-7,oy-7,15,15);
    g.drawOval(ox-8,oy-8,17,17);
    }
  public int findPin(int hx,int hy) {
    int p,w;
    p=numPins();
    if (p<=20) w=3; else w=6;
    if ((y==hy-w) && (hx>=x) && (hx<x+(p/2)))
      return p-(hx-x);
    else if ((y==hy) && (hx>=x) && (hx<x+(p/2)))
      return 1+hx-x;
    else return 0;
    }
  public int getPinX(int p) {
    return x;
    }
  public int getPinY(int p) {
    if (p==1) return y;
      else return y+3;
    }
  }

