package dce;

import parts.*;
import dce.*;
import shared.*;
import java.awt.*;

public class Wire {
  public parts.Component startChip;
  public int startPin;
  public int startIO;
  public parts.Component endChip;
  public int endPin;
  public int endIO;
  public int net;
  public Color color;
  public Wire(parts.Component stch,int stpin,parts.Component ench,int enpin,Color clr) {
    startChip=stch;
    startPin=stpin;
    endChip=ench;
    endPin=enpin;
    color=clr;
    net=1;
    startIO=startChip.pinOut(startPin);
    endIO=endChip.pinOut(endPin);
    }
  public void draw(Graphics g,int offsetX,int offsetY,int nt) {
    int x1,y1,x2,y2;
    int ox1,ox2,oy1,oy2;
    if (nt==0 || nt==net) {
      g.setColor(color);
      x1=startChip.getPinX(startPin)-offsetX;
      y1=startChip.getPinY(startPin)-offsetY;
      x2=endChip.getPinX(endPin)-offsetX;
      y2=endChip.getPinY(endPin)-offsetY;
      x1*=8; x2*=8; y1*=8; y2*=8;
      if (x2>x1) ox1=3; else ox1=-3;
      if (x2>=x1) ox2=-3; else ox2=3;
      if (y2<=y1) oy1=-3; else oy1=3;
      if (y2<y1) oy2=3; else oy2=-3;
      g.drawLine(x1+ox1,y1+oy1,x2+ox2,y2+oy2);
      g.drawLine(x1,y1,x1+ox1,y1+oy1);
      g.drawLine(x2,y2,x2+ox2,y2+oy2);
      }
    }
  public void setNet(int n) {
    net=n;
    }
  public int getNet() {
    return net;
    }
  public int colorToInt() {
    int ret=0;
    if (color==Color.black) ret=1;
    if (color==Color.blue) ret=2;
    if (color==Color.cyan) ret=3;
    if (color==Color.gray) ret=4;
    if (color==Color.green) ret=5;
    if (color==Color.magenta) ret=6;
    if (color==Color.orange) ret=7;
    if (color==Color.pink) ret=8;
    if (color==Color.red) ret=9;
    if (color==Color.white) ret=10;
    if (color==Color.yellow) ret=11;
    if (color==Globals.darkRed) ret=12;
    if (color==Globals.darkGreen) ret=13;
    if (color==Globals.darkBlue) ret=14;
    if (color==Globals.darkCyan) ret=15;
    if (color==Globals.darkYellow) ret=16;
    return ret;
    }
  public void setIntColor(int clr) {
    if (clr==1) color=Color.black;
    if (clr==2) color=Color.blue;
    if (clr==3) color=Color.cyan;
    if (clr==4) color=Color.gray;
    if (clr==5) color=Color.green;
    if (clr==6) color=Color.magenta;
    if (clr==7) color=Color.orange;
    if (clr==8) color=Color.pink;
    if (clr==9) color=Color.red;
    if (clr==10) color=Color.white;
    if (clr==11) color=Color.yellow;
    if (clr==12) color=Globals.darkRed;
    if (clr==13) color=Globals.darkGreen;
    if (clr==14) color=Globals.darkBlue;
    if (clr==15) color=Globals.darkCyan;
    if (clr==16) color=Globals.darkYellow;
    }
  }
