package parts;

import java.awt.*;
import java.io.*;
import parts.*;

public class Component {
  public int[] pin;
  public int x;
  public int y;
  public Component() {
    }
  public void init(Frame window) {
    }
  public void load(String buffer) {
    int j=0;
    StringBuffer buffer2=new StringBuffer();
    buffer2.setLength(0);
    while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
    x=(new Integer(buffer2.toString())).intValue();
    j++;
    buffer2.setLength(0);
    while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
    y=(new Integer(buffer2.toString())).intValue();
    }
  public String save() {
    int j=0;
    StringBuffer buffer=new StringBuffer();
    buffer.setLength(0);
    buffer.append(toString());
    j=0;
    while (j<buffer.length() && buffer.charAt(j)!='@') j++;
    buffer.setLength(j);
    buffer.append(" ");
    buffer.append(x);
    buffer.append(" ");
    buffer.append(y);
    buffer.append("\n");
    return buffer.toString();
    }
  public String partName() {
    int j=0;
    StringBuffer buffer=new StringBuffer();
    buffer.setLength(0);
    buffer.append(toString());
    j=0;
    while (j<buffer.length() && buffer.charAt(j)!='@') j++;
    buffer.setLength(j);
    return buffer.toString().substring(1,buffer.length());
    }
  public int pressed(int hx,int hy) {
    return 0;
    }
  public int released(int hx,int hy) {
    return 0;
    }
  public int numPins() {
    return 0;
    }
  public int pinOut(int pn) {
    return 0;
    }
  public int run() {
    return 0;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    }
  public void reset() {
    int i;
    for (i=0;i<numPins();i++) pin[i]=2;
    }
  public void setLocation(int lx,int ly) {
    x=lx; y=ly;
    }
  public int findPin(int hx,int hy) {
    int p,w;
    p=numPins();
    if (p<=20) w=3; else w=6;
    if ((y==hy-w) && (hx>=x) && (hx<x+(p/2)))
      return p-(hx-x);
    else if ((y==hy) && (hx>=x) && (hx<x+(p/2)))
      return 1+hx-x;
    else return 0;
    }
  public int getX() {
    return x;
    }
  public int getY() {
    return y;
    }
  public int getPinX(int p) {
    return 0;
    }
  public int getPinY(int p) {
    return 0;
    }
  public int f_and(int a,int b) {
    if (a!=0 && b!=0) return 1; else return 0;
    }
  public int f_nand(int a,int b) {
    if (a!=0 && b!=0) return 0; else return 1;
    }
  public int f_and3(int a,int b,int c) {
    if (a!=0 && b!=0 && c!=0) return 1; else return 0;
    }
  public int f_nand3(int a,int b,int c) {
    if (a!=0 && b!=0 && c!=0) return 0; else return 1;
    }
  public int f_and4(int a,int b,int c,int d) {
    if (a!=0 && b!=0 && c!=0 && d!=0) return 1; else return 0;
    }
  public int f_nand4(int a,int b,int c,int d) {
    if (a!=0 && b!=0 && c!=0 && d!=0) return 0; else return 1;
    }
  public int f_and5(int a,int b,int c,int d,int e) {
    if (a!=0 && b!=0 && c!=0 && d!=0 && e!=0) return 1; else return 0;
    }
  public int f_nand5(int a,int b,int c,int d,int e) {
    if (a!=0 && b!=0 && c!=0 && d!=0 && e!=0) return 0; else return 1;
    }
  public int f_or(int a,int b) {
    if (a!=0 || b!=0) return 1; else return 0;
    }
  public int f_nor(int a,int b) {
    if (a!=0 || b!=0) return 0; else return 1;
    }
  public int f_nor3(int a,int b,int c) {
    if (a!=0 || b!=0 || c!=0) return 0; else return 1;
    }
  public int f_or4(int a,int b,int c,int d) {
    if (a!=0 || b!=0 || c!=0 || d!=0) return 1; else return 0;
    }
  public int f_nor4(int a,int b,int c,int d) {
    if (a!=0 || b!=0 || c!=0 || d!=0) return 0; else return 1;
    }
  public int f_nor5(int a,int b,int c,int d,int e) {
    if (a!=0 || b!=0 || c!=0 || d!=0 || e!=0) return 0; else return 1;
    }
  public int f_inv(int a) {
    if (a!=0) return 0; else return 1;
    }
  public int f_xor(int a,int b) {
    if ((a!=0 && b!=0) || (a==0 && b==0)) return 0; else return 1;
    }
  public int f_xnor(int a,int b) {
    if ((a!=0 && b!=0) || (a==0 && b==0)) return 1; else return 0;
    }
  public int f_tri(int a,int b) {
    int flag;
    flag=2;
    if (b==0) {
      if (a!=0)  flag=1; else flag=0;
      }
    return flag;
    }
  public int f_binin(int a,int b,int c,int d,int e,int f,int g,int h) {
    int ret=0;
    if (a!=0) ret=1; else ret=0;
    if (b!=0) ret+=2;
    if (c!=0) ret+=4;
    if (d!=0) ret+=8;
    if (e!=0) ret+=16;
    if (f!=0) ret+=32;
    if (g!=0) ret+=64;
    if (h!=0) ret+=128;
    return ret;
    }
  public void dff(int s,int r,int c,int d,int q,int nq,int c2) {
    if ((pin[s]!=0) && (pin[r]==0))
      {
        pin[q]=0; pin[nq]=1;
     }
    if ((pin[s]==0) && (pin[r]!=0))
      {
        pin[q]=1; pin[nq]=0;
     }
    if ((pin[s]==0) && (pin[r]==0))
      {
        pin[q]=1; pin[nq]=1;
     }
    if ((pin[s]!=0) && (pin[r]!=0))
      {
        if ((pin[c]!=0) && (pin[c2]==0))
          {
            if (pin[d]==0)
              {
                pin[q]=0; pin[nq]=1;
             }
           else
              {
                pin[q]=1; pin[nq]=0;
             }
         }
     }
    pin[c2]=pin[c];
    }
  public void disperse(int pn,int p1,int p2,int p3,int p4, int p5,
                       int p6,int p7,int p8) {
    if ((pin[pn] & 1)==1)  pin[p1]=1; else pin[p1]=0;
    if ((pin[pn] & 2)==2)  pin[p2]=1; else pin[p2]=0;
    if ((pin[pn] & 4)==4)  pin[p3]=1; else pin[p3]=0;
    if ((pin[pn] & 8)==8)  pin[p4]=1; else pin[p4]=0;
    if ((pin[pn] & 16)==16)  pin[p5]=1; else pin[p5]=0;
    if ((pin[pn] & 32)==32)  pin[p6]=1; else pin[p6]=0;
    if ((pin[pn] & 64)==64)  pin[p7]=1; else pin[p7]=0;
    if ((pin[pn] & 128)==128)  pin[p8]=1; else pin[p8]=0;
    }
  public void jkff(int s,int r,int c,int j,int k,int q,int nq,int c2)
  {
    int x;
    if ((pin[s]!=0) && (pin[r]==0))
      {
        pin[q]=0; pin[nq]=1;
     }
    if ((pin[s]==0) && (pin[r]!=0))
      {
        pin[q]=1; pin[nq]=0;
     }
    if ((pin[s]==0) && (pin[r]==0))
      {
        pin[q]=1; pin[nq]=1;
     }
    if ((pin[s]!=0) && (pin[r]!=0))
      {
        if ((pin[c]==0) && (pin[c2]!=0))
          {
            if ((pin[j]==0) && (pin[k]!=0))
              {
                pin[q]=0; pin[nq]=1;
             }
            if ((pin[j]!=0) && (pin[k]==0))
              {
                pin[q]=1; pin[nq]=0;
             }
            if ((pin[j]!=0) && (pin[k]!=0))
              {
                x=pin[q];
                pin[q]=pin[nq];
                pin[nq]=x;
             }
         }
     }
    pin[c2]=pin[c];
  }
  public void srff(int s,int r,int q,int nq) {
    if ((pin[s]!=0) && (pin[r]==0))
      {
        pin[q]=0; pin[nq]=1;
     }
    if ((pin[s]==0) && (pin[r]!=0))
      {
        pin[q]=1; pin[nq]=0;
     }
    if ((pin[s]==0) && (pin[r]==0))
      {
        pin[q]=1; pin[nq]=1;
     }
    }
  public void decode(int v,int a,int b,int c,int d,int e,int f,int g,int h) {
    pin[a]=1; pin[b]=1; pin[c]=1; pin[d]=1;
    pin[e]=1; pin[f]=1; pin[g]=1; pin[h]=1;
    switch (v) {
      case 0:pin[a]=0; break;
      case 1:pin[b]=0; break;
      case 2:pin[c]=0; break;
      case 3:pin[d]=0; break;
      case 4:pin[e]=0; break;
      case 5:pin[f]=0; break;
      case 6:pin[g]=0; break;
      case 7:pin[h]=0; break;
      }
    }

  }
