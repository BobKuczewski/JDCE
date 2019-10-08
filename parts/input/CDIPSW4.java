package parts.input;
import java.awt.*;
import dce.*;
import parts.*;
public class CDIPSW4 extends Ic {
  int switchOn;
  public CDIPSW4() {
    super();
    name=new String("DIP4");
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
    return 8;
    }
  public int pinOut(int pn) {
    if (pn>4) return 1; else return 0;
    }
  public int run() {
    if ((switchOn &1)==1) pin[8]=pin[1]; else pin[8]=2;
    if ((switchOn &2)==2) pin[7]=pin[2]; else pin[7]=2;
    if ((switchOn &4)==4) pin[6]=pin[3]; else pin[6]=2;
    if ((switchOn &8)==8) pin[5]=pin[4]; else pin[5]=2;
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
    }
  public int pressed(int hx,int hy) {
    int pn;
    pn=findPin(hx,hy);
    if (pn==1) switchOn&=0xfe;
    if (pn==2) switchOn&=0xfd;
    if (pn==3) switchOn&=0xfb;
    if (pn==4) switchOn&=0xf7;
    if (pn==5) switchOn|=8;
    if (pn==6) switchOn|=4;
    if (pn==7) switchOn|=2;
    if (pn==8) switchOn|=1;
    return 1;
    }
  }

