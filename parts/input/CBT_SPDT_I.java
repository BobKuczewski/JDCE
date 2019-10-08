package parts.input;
import java.awt.*;
import dce.*;
import parts.*;
public class CBT_SPDT_I extends Switch {
  public CBT_SPDT_I() {
    super();
    pin=new int[4];
    switchOn=0;
    }
  public int numPins() {
    return 3;
    }
  public int pinOut(int pn) {
    if (pn==2) return 1; else return 0;
    }
  public int run() {
    if (switchOn==0) pin[2]=pin[1]; else pin[2]=pin[3];
    return 0;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    g.setColor(Color.white);
    g.drawRect(ox-2,oy-2,5,5);
    g.drawLine(ox,oy+2,ox,oy+5);
    oy+=8;
    g.drawOval(ox-2,oy-2,5,5);
    g.drawOval(ox-10,oy-10,21,21);
    oy+=8;
    g.drawRect(ox-2,oy-2,5,5);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    oy-=8;
    g.setColor(Color.lightGray);
    g.fillOval(ox-10,oy-10,21,21);
    g.setColor(Color.black);
    g.fillOval(ox-4,oy-4,9,9);
    g.setColor(Color.white);
    if (switchOn!=1) {
      g.drawLine(ox-1,oy+10,ox-1,oy);
      g.drawLine(ox,oy+10,ox,oy);
      g.drawLine(ox+1,oy+10,ox+1,oy);
      g.drawLine(ox-2,oy+8,ox-2,oy+1);
      g.drawLine(ox+2,oy+8,ox+2,oy+1);
      } else {
      g.drawLine(ox-1,oy-10,ox-1,oy);
      g.drawLine(ox,oy-10,ox,oy);
      g.drawLine(ox+1,oy-10,ox+1,oy);
      g.drawLine(ox-2,oy-8,ox-2,oy-1);
      g.drawLine(ox+2,oy-8,ox+2,oy-1);
      }
    }
  public int findPin(int hx,int hy) {
    int p,w;
    if (hx==x && hy==y) return 1;
      else if (hx==x && hy==y+1) return 2;
      else if (hx==x && hy==y+2) return 3;
      else return 0;
    }
  public int getPinX(int p) {
    return x;
    }
  public int getPinY(int p) {
    int ret=0;
    if (p==1) ret=y;
      else if (p==2) ret=y+1;
      else if (p==3) ret=y+2;
    return ret;
    }
  }

