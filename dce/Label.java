package dce;

import java.awt.*;

public class Label {
  String text;
  int x,y;
  public Label(String txt) {
    text=new String(txt);
    x=0; y=0;
    }
  public int getX() {
    return x;
    }
  public int getY() {
    return y;
    }
  public String getText() {
    return text;
    }
  public void setLocation(int nx,int ny) {
    x=nx;
    y=ny;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int pins;
    int ox,oy;
    int w,i;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    g.setColor(Color.white);
    g.drawString(text,ox,oy);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int pins;
    int ox,oy;
    int w,i;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8+8;
    g.setColor(Color.white);
    g.drawString(text,ox,oy);
    }
  }

