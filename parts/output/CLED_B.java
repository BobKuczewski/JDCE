package parts.output;
import java.awt.*;
import parts.*;
import dce.*;
import shared.*;
public class CLED_B extends parts.Component {
  int state,last;
  public CLED_B() {
    super();
    pin=new int[41];
    }
  public void reset() {
    super.reset();
    state=0; last=0;
    }
  public int numPins() {
    return 2;
    }
  public int pinOut(int pn) {
    return 0;
    }
  public int run() {
    int ret;
    ret=0;
    if (pin[1]==0 && pin[2]==1) state=1; else state=0;
    if (state != last) { ret=1; last=state; }
    return ret;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    oy+=4;
    g.setColor(Color.white);
    g.drawOval(ox-6,oy-6,13,13);
    g.drawLine(ox-5,oy-2,ox+5,oy-2);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    g.setColor(Globals.darkBlue);
    g.fillOval(ox-6,oy-6,13,13);
    if (state!=0) {
      g.setColor(Color.blue);
      g.fillOval(ox-4,oy-4,9,9);
      }
    }
  public int findPin(int hx,int hy) {
    int p,w;
    if (hx==x && hy==y) return 1;
      else if (hx==x && hy==y+1) return 2;
      else return 0;
    }
  public int getPinX(int p) {
    return x;
    }
  public int getPinY(int p) {
    if (p==1) return y;
      else if (p==2) return y+1;
      else return 0;
    }
  }

