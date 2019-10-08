package parts.output;
import java.awt.*;
import parts.*;
import dce.*;
import shared.*;
public class CTIL311 extends parts.Component {
  int state,last;
  int dpstate,dplast;
  public CTIL311() {
    super();
    pin=new int[41];
    }
  public void reset() {
    super.reset();
    state=0; last=0;
    dpstate=0; dplast=0;
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
    if (pin[4]!=pin[32])  pin[30]=99;
    if (pin[10]!=pin[33])  pin[30]=99;
    if (pin[8]!=pin[31])  pin[30]=99;
    if (pin[5]==0)
      {
        if (pin[3]!=0)  pin[20]=1; else pin[20]=0;
        if (pin[2]!=0)  pin[20]+=2;
        if (pin[13]!=0)  pin[20]+=4;
        if (pin[12]!=0)  pin[20]+=8;
     }
    pin[30]=pin[20];
    pin[31]=pin[8];
    pin[32]=pin[4];
    pin[33]=pin[10];
  
    if (pin[1]==1 && pin[8]==0) state=pin[20];
      else state=-1;
    if (state != last) { ret=1; last=state; }
    if (pin[4]==0 && pin[1]==1) dpstate=1; else dpstate=0;
    if (pin[10]==0 && pin[1]==1) dpstate+=2;
    if (dpstate != dplast) { ret=1; dplast=dpstate; }
    return ret;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int i;
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    g.setColor(Color.white);
    for (i=0;i<7;i++) {
      if (i!=5) g.drawOval(ox-2,oy-2-i*8,5,5);
      if (i!=3 && i!=5) g.drawOval(ox+22,oy-2-i*8,5,5);
      }
    g.drawRect(ox-4,oy-52,32,58);
    g.drawLine(ox+5,oy,ox+7,oy);
    g.drawLine(ox+5,oy-1,ox+7,oy-1);
    }
  private void spot(Graphics g,int x,int y) {
    g.drawLine(x,y,x+1,y);
    g.drawLine(x,y+1,x+1,y+1);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    g.setColor(Color.black);
    g.fillRect(ox-4,oy-4,32,56);
    ox+=5; oy+=2;
    switch (state) {
      case 0: case 2: case 3: case 5: case 6: case 7: case 8: case 9:
      case 10: case 11: case 12: case 13: case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+4,oy); spot(g,ox+8,oy);
    switch (state) {
        case 0: case 1: case 2: case 3: case 4: case 7: case 8: case 9:
        case 10: case 11: case 13:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+12,oy+4); spot(g,ox+12,oy+8);
    switch (state) {
      case 0: case 1: case 3: case 4: case 5: case 6: case 7: case 8:
      case 9: case 10: case 11: case 13:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+12,oy+16); spot(g,ox+12,oy+20);
    switch (state) {
      case 0: case 2: case 3: case 5: case 6: case 8: case 9: case 11:
      case 12: case 13: case 14:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+4,oy+24); spot(g,ox+8,oy+24);
    switch (state) {
      case 0: case 2: case 6: case 8: case 10: case 11: case 12: case 13:
      case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox,oy+16); spot(g,ox,oy+20);
    switch (state) {
      case 0: case 4: case 5: case 6: case 8: case 9: case 10: case 11:
      case 12: case 13: case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox,oy+4); spot(g,ox,oy+8);
    switch (state) {
      case 2: case 3: case 4: case 5: case 6: case 8: case 9: case 10:
      case 11: case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+4,oy+12); spot(g,ox+8,oy+12);
    switch (state) {
      case 2: case 3: case 4: case 5: case 7: case 11: case 13:
      case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox,oy);
    switch (state) {
      case 1: case 5: case 7: case 12: case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+12,oy);
    switch (state) {
      case 0: case 1: case 4: case 7: case 9: case 10: case 13:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+12,oy+12);
    switch (state) {
      case 1: case 2: case 4: case 7: case 10: case 12: case 14:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox+12,oy+24);
    switch (state) {
      case 2: case 3: case 5: case 10: case 11: case 13: case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox,oy+24);
    switch (state) {
      case 0: case 4: case 5: case 6: case 10: case 11: case 12: case 13:
      case 14: case 15:
        g.setColor(Color.red); break;
      default: g.setColor(Globals.darkRed);
      }
    spot(g,ox,oy+12);
    if ((dpstate & 1)==0) g.setColor(Globals.darkRed);
      else g.setColor(Color.red);
    spot(g,ox-6,oy+24);
    if ((dpstate & 2)==0) g.setColor(Globals.darkRed);
      else g.setColor(Color.red);
    spot(g,ox+18,oy+24);
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
