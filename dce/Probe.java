package dce;

import dce.*;
import parts.*;
import java.awt.*;

public class Probe {
  public parts.Component part;
  public int       pin;
  public String    name;
  public Probe(parts.Component prt,int pn,String nm) {
    part=prt;
    pin=pn;
    name=nm;
    }
  public String getName() {
    return name;
    }
  public parts.Component getPart() {
    return part;
    }
  public int getPin() {
    return pin;
    }
  public void draw(Graphics g,int offsetX,int offsetY) {
    int x,y;
    x=part.getPinX(pin)-offsetX;
    y=part.getPinY(pin)-offsetY;
    x*=8; y*=8;
    g.setColor(Color.red);
    g.drawLine(x-1,y-2,x+1,y-2);
    g.drawLine(x-2,y-1,x+2,y-1);
    g.drawLine(x-2,y,x+2,y);
    g.drawLine(x-2,y+1,x+2,y+1);
    g.drawLine(x-1,y+2,x+1,y+2);
    }
  }

