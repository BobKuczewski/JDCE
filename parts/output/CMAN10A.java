package parts.output;
import java.awt.*;
import parts.*;
import dce.*;
import shared.*;
public class CMAN10A extends parts.Component {
  int state,last;
  public CMAN10A() {
    super();
    pin=new int[41];
    }
  public void reset() {
    super.reset();
    state=0; last=0;
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    return 0;
    }
  public int run() {
    int ret,anode;
    ret=0;
    if (pin[3]==1 || pin[14]==1) {
      if (pin[1]==0) state=1; else state=0;
      if (pin[13]==0) state+=2;
      if (pin[10]==0) state+=4;
      if (pin[8]==0) state+=8;
      if (pin[7]==0) state+=16;
      if (pin[2]==0) state+=32;
      if (pin[11]==0) state+=64;
      if (pin[6]==0) state+=128;
      } else state=0;
    if (state != last) { ret=1; last=state; }
    return ret;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int i;
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    g.setColor(Color.white);
    for (i=0;i<7;i++) {
      g.drawOval(ox-2,oy-2-i*8,5,5);
      g.drawOval(ox+22,oy-2-i*8,5,5);
      }
    g.drawRect(ox-4,oy-52,32,58);
    g.drawLine(ox+5,oy,ox+7,oy);
    g.drawLine(ox+5,oy-1,ox+7,oy-1);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    g.setColor(Color.black);
    g.fillRect(ox-4,oy-4,32,56);
    ox+=2; oy+=2;
    if ((state & 1)==1) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+3,oy,ox+15,oy); g.drawLine(ox+3,oy+1,ox+15,oy+1);
    if ((state & 2)==2) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+16,oy+2,ox+16,oy+14); g.drawLine(ox+17,oy+2,ox+17,oy+14);
    if ((state & 4)==4) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+16,oy+17,ox+16,oy+29); g.drawLine(ox+17,oy+17,ox+17,oy+29);
    if ((state & 8)==8) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+3,oy+30,ox+15,oy+30); g.drawLine(ox+3,oy+31,ox+15,oy+31);
    if ((state & 16)==16) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+1,oy+17,ox+1,oy+29); g.drawLine(ox+2,oy+17,ox+2,oy+29);
    if ((state & 32)==32) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+1,oy+2,ox+1,oy+14); g.drawLine(ox+2,oy+2,ox+2,oy+14);
    if ((state & 64)==64) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox+3,oy+15,ox+15,oy+15); g.drawLine(ox+3,oy+16,ox+15,oy+16);
    if ((state & 128)==128) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.drawLine(ox-4,oy+30,ox-3,oy+30); g.drawLine(ox-4,oy+31,ox-3,oy+31);
    }
  public int findPin(int hx,int hy) {
    int p,w;
    if (hx==x && hy<=y && hy>y-7) return y-hy+1;
      else if (hx==x+3 && hy<=y && hy>y-7) return 14-(y-hy);
      else return 0;
    }
  public int getPinX(int p) {
    if (p<8) return x;
      else if (p<15) return x+3;
      else return 0;
    }
  public int getPinY(int p) {
    if (p<8) return (y-(p-1));
      else if (p<15) return (y-(14-p));
      else return 0;
    }
  }

