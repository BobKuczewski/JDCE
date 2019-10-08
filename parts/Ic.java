package parts;

import java.awt.*;
import java.io.*;
import parts.*;
import dce.*;
import shared.*;

public class Ic extends Component {
  public String name;
  public Ic() {
    x=5;
    y=5;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int pins;
    int ox,oy;
    int w,i;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    pins=numPins()/2;
    if (pins<=10) w=3; else w=6;
    g.setColor(Color.white);
    g.drawRect(ox-4,oy+2,8*pins,w*8-4);
    g.drawLine(ox-1,oy+5,ox+1,oy+5);
    g.drawLine(ox,oy+4,ox,oy+6);
    g.drawString(name,ox-2,oy+16);
    for (i=0;i<pins;i++) {
      g.drawLine(ox+(i*8)-1,oy-2,ox+(i*8)+1,oy-2);
      g.drawLine(ox+(i*8)-1,oy+2,ox+(i*8)+1,oy+2);
      g.drawLine(ox+(i*8)-2,oy-1,ox+(i*8)-2,oy+1);
      g.drawLine(ox+(i*8)+2,oy-1,ox+(i*8)+2,oy+1);
      g.drawLine(ox+(i*8)-1,oy-2+(w*8),ox+(i*8)+1,oy-2+(w*8));
      g.drawLine(ox+(i*8)-1,oy+2+(w*8),ox+(i*8)+1,oy+2+(w*8));
      g.drawLine(ox+(i*8)-2,oy-1+(w*8),ox+(i*8)-2,oy+1+(w*8));
      g.drawLine(ox+(i*8)+2,oy-1+(w*8),ox+(i*8)+2,oy+1+(w*8));
      }
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int pins;
    int ox,oy;
    int w,i;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    pins=numPins()/2;
    if (pins<=10) w=3; else w=6;
    g.setColor(Globals.darkGray);
    g.fillRect(ox-4,oy-w*8+2,8*pins,w*8-4);

    g.setColor(Color.white);
    g.drawLine(ox-1,oy-5,ox+1,oy-5);
    g.drawLine(ox,oy-4,ox,oy-6);
    g.drawString(name,ox-2,oy-8);
    g.setColor(Globals.lightGray);
    for (i=0;i<pins;i++) {
      g.drawLine(ox+(i*8)-2,oy,ox+(i*8)+2,oy);
      g.drawLine(ox+(i*8)-2,oy-1,ox+(i*8)+2,oy-1);
      g.drawLine(ox+(i*8)-2,oy-2,ox+(i*8)+2,oy-2);

      g.drawLine(ox+(i*8)-2,oy-(w*8),ox+(i*8)+2,oy-(w*8));
      g.drawLine(ox+(i*8)-2,oy-1-(w*8),ox+(i*8)+2,oy-1-(w*8));
      g.drawLine(ox+(i*8)-2,oy+1-(w*8),ox+(i*8)+2,oy+1-(w*8));
      }
    }
  public int getPinX(int p) {
    int pn;
    pn=numPins()/2;
    if (p<=pn) return x+(p-1);
      else return x+(numPins()-p);
    }
  public int getPinY(int p) {
    int pn,w;
    pn=numPins()/2;
    if (pn<=10) w=3; else w=6;
    if (p<=pn) return y; else return y+w;
    }
  }
