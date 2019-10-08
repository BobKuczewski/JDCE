package parts.input;
import java.awt.*;
import dce.*;
import parts.*;
public class CDIPSW8 extends Ic {
  int switchOn;
  public CDIPSW8() {
    super();
    name=new String("DIP8");
    pin=new int[41];
    switchOn=0;
    }
  public void load(String buffer) {
    int j=0;
    StringBuffer buffer2=new StringBuffer();
    buffer2.setLength(0);
    while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
    x=(new Integer(buffer2.toString())).intValue();
    j++;
    buffer2.setLength(0);
    while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
    y=(new Integer(buffer2.toString())).intValue();
    j++;
    buffer2.setLength(0);
    while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
    switchOn=(new Integer(buffer2.toString())).intValue();
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
    buffer.append(" ");
    buffer.append(switchOn);
    buffer.append("\n");
    return buffer.toString();
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn>8) return 1; else return 0;
    }
  public int run() {
    if ((switchOn &1)==1) pin[16]=pin[1]; else pin[16]=2;
    if ((switchOn &2)==2) pin[15]=pin[2]; else pin[15]=2;
    if ((switchOn &4)==4) pin[14]=pin[3]; else pin[14]=2;
    if ((switchOn &8)==8) pin[13]=pin[4]; else pin[13]=2;
    if ((switchOn &16)==16) pin[12]=pin[5]; else pin[12]=2;
    if ((switchOn &32)==32) pin[11]=pin[6]; else pin[11]=2;
    if ((switchOn &64)==64) pin[10]=pin[7]; else pin[10]=2;
    if ((switchOn &128)==128) pin[9]=pin[8]; else pin[9]=2;
    return 0;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    int pins;
    int ox,oy;
    int w,i;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    pins=numPins()/2;
    if (pins<=10) w=3; else w=6;
    g.setColor(Color.white);
    g.drawRect(ox-4,oy+2,8*pins,w*8-4);
    g.drawLine(ox-1,oy+5,ox+1,oy+5);
    g.drawLine(ox,oy+4,ox,oy+6);
    g.drawString(name,ox,oy+16);
    for (i=0;i<pins;i++) {
      g.drawRect(ox+(i*8)-2,oy-3,5,5);
      g.drawOval(ox+(i*8)-2,oy-2+(w*8),5,5);
      }
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int pins;
    int ox,oy;
    int w,i;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    pins=numPins()/2;
    if (pins<=10) w=3; else w=6;
    g.setColor(Color.darkGray);
    g.fillRect(ox-4,oy-w*8-2,8*pins,w*8+4);
    g.setColor(Color.black);
    for (i=0;i<numPins()/2;i++) {
      g.fillRect(ox-2+(i*8),oy-22,5,20);
      }
    g.setColor(Color.lightGray);
    if ((switchOn & 1)==1) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 2)==2) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 4)==4) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 8)==8) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 16)==16) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 32)==32) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 64)==64) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    ox+=8;
    if ((switchOn & 128)==128) g.fillRect(ox-2,oy-22,5,8);
      else g.fillRect(ox-2,oy-10,5,8);
    }
  public int pressed(int hx,int hy) {
    int pn;
    pn=findPin(hx,hy);
    if (pn==1) switchOn&=0xfe;
    if (pn==2) switchOn&=0xfd;
    if (pn==3) switchOn&=0xfb;
    if (pn==4) switchOn&=0xf7;
    if (pn==5) switchOn&=0xef;
    if (pn==6) switchOn&=0xdf;
    if (pn==7) switchOn&=0xbf;
    if (pn==8) switchOn&=0x7f;

    if (pn==9 ) switchOn|=128;
    if (pn==10) switchOn|=64;
    if (pn==11) switchOn|=32;
    if (pn==12) switchOn|=16;
    if (pn==13) switchOn|=8;
    if (pn==14) switchOn|=4;
    if (pn==15) switchOn|=2;
    if (pn==16) switchOn|=1;
    return 1;
    }
  }
