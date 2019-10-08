package dce;

import java.awt.*;
import java.util.*;

public class Pinout extends Canvas {
  int numPins;
  Vector pinLabels;
  String name;
  public Pinout(String nm,int pins) {
    name=nm;
    setSize(200,400);
    setBackground(Color.white);
    numPins=pins;
    pinLabels=new Vector();
    }
  public void setName(String nm) { name=nm; }
  public void setPins(int pins) {
    numPins=pins;
    }
  public void addLabel(String label) {
    pinLabels.addElement(label);
    }
  private void pintextxy(Graphics g,int x,int y,String txt) {
    char character;
    char[] chars=new char[2];
    int  inv=0;
    int  i;
    for (i=0;i<txt.length();i++) {
      chars[0]=txt.charAt(i);
      if (chars[0]>='a' && chars[0]<='z') {
        inv=1;
        chars[0]=Character.toUpperCase(chars[0]);
        } else if (chars[0]>='A' && chars[0]<='Z') inv=0;
      g.drawChars(chars,0,1,x,y+8);
      if (inv==1)
        g.drawLine(x,y-3,x+8,y-3);
      x+=8;
      }
    }
  private void g_num(Graphics g,int x,int y,int n) {
    if (n==0 || n==2 || n==3 || n==5 || (n>=7 && n<=9))
      g.drawLine(x,y,x+2,y);
    if ((n>=0 && n<=4) || (n>=7 && n<=9))
      g.drawLine(x+2,y,x+2,y+2);
    if (n==0 || n==1 || (n>=3 && n<=9))
      g.drawLine(x+2,y+2,x+2,y+4);
    if (n==0 || n==2 || n==3 || n==5 || n==6 || n==8)
      g.drawLine(x,y+4,x+2,y+4);
    if (n==0 || n==2 || n==6 || n==8)
      g.drawLine(x,y+4,x,y+2);
    if (n==0 || n==4 || n==5 || n==6 || n==8 || n==9)
      g.drawLine(x,y+2,x,y);
    if ((n>=2 && n<=6) || n==8 || n==9)
      g.drawLine(x,y+2,x+2,y+2);
    }
  private void showNum(Graphics g,int x,int y,int n) {
    if (n>9) {
      g_num(g,x,y,(n/10));
      n=n-(n/10)*10;
      }
    x+=4;
    g_num(g,x,y,n);
    }
  public void paint(Graphics g) {
    int scale,diff,j,i,nt,pin2,pins,x,y;
    String label;
    g.setColor(Color.black);
    g.drawLine(199,0,199,768);
    pin2=numPins; pins=pin2;
    pins/=2;
    x=0; y=25;
    if (pins<13) scale=15; else scale=10;
    if (pins<13) diff=5; else diff=3;
    scale=15; diff=5;
    g.drawRect(x+70,y+scale,60,y+scale*(pins-1));
    g.drawRect(x+95,y+scale,10,7);
    for (i=1;i<=pins;i++) {
      g.drawRect(x+60,y+scale*i+diff-1,10,8);
      g.drawRect(x+130,y+scale*i+diff-1,10,8);
      showNum(g,x+62,y+scale*i+diff+1,i);
      showNum(g,x+132,y+scale*i+diff+1,(pins*2)-i+1);
      if (pinLabels.size()>=i) {
        label=(String)pinLabels.elementAt(i-1);
        pintextxy(g,x+59-8*label.length(),y+scale*i+diff,label);
        }
      if (pinLabels.size()>=(pins*2)-i) {
        label=(String)pinLabels.elementAt(pins*2-i);
        pintextxy(g,x+146,y+scale*i+diff,label);
        }
      }
    g.drawString(name,x+70,y+10);
    }
  }
