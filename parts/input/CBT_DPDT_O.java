package parts.input;
import java.awt.*;
import dce.*;
import parts.*;
public class CBT_DPDT_O extends Switch {
  public CBT_DPDT_O() {
    super();
    pin=new int[7];
    switchOn=0;
    }
  public int numPins() {
    return 6;
    }
  public int pinOut(int pn) {
    if (pn==1 || pn==3 || pn==4 || pn==6) return 1; else return 0;
    }
  public int run() {
    pin[1]=2; pin[3]=2; pin[4]=2; pin[6]=2;
    if (switchOn==0) pin[1]=pin[2]; else pin[3]=pin[2];
    if (switchOn==0) pin[4]=pin[5]; else pin[6]=pin[5];
    return 0;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    g.setColor(Color.white);
    g.drawOval(ox-2,oy-2,5,5);
    g.drawOval(ox-2+8,oy-2,5,5);
    g.drawLine(ox,oy+2,ox,oy+5);
    g.drawLine(ox+8,oy+2,ox+8,oy+5);
    oy+=8;
    g.drawRect(ox-4,oy-12,17,25);
    g.drawRect(ox-2,oy-2,5,5);
    g.drawRect(ox-2+8,oy-2,5,5);
    oy+=8;
    g.drawOval(ox-2,oy-2,5,5);
    g.drawOval(ox-2+8,oy-2,5,5);
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
      else if (hx==x+1 && hy==y) return 4;
      else if (hx==x+1 && hy==y+1) return 5;
      else if (hx==x+1 && hy==y+2) return 6;
      else return 0;
    }
  public int getPinX(int p) {
    if (p<4) return x;
      else return x+1;
    }
  public int getPinY(int p) {
    int ret=0;
    if (p==1 || p==4) ret=y;
      else if (p==2 || p==5) ret=y+1;
      else if (p==3 || p==6) ret=y+2;
    return ret;
    }
  }

