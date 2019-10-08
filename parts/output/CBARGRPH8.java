package parts.output;
import java.awt.*;
import parts.*;
import dce.*;
import shared.*;
public class CBARGRPH8 extends Ic {
  int state,last;
  public CBARGRPH8() {
    super();
    name=new String("Bar8");
    pin=new int[41];
    }
  public void reset() {
    super.reset();
    state=0; last=0;
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    return 0;
    }
  public int run() {
    int ret;
    ret=0;
    if (pin[1]==0 && pin[16]==1) state=1; else state=0;
    if (pin[2]==0 && pin[15]==1) state+=2;
    if (pin[3]==0 && pin[14]==1) state+=4;
    if (pin[4]==0 && pin[13]==1) state+=8;
    if (pin[5]==0 && pin[12]==1) state+=16;
    if (pin[6]==0 && pin[11]==1) state+=32;
    if (pin[7]==0 && pin[10]==1) state+=64;
    if (pin[8]==0 && pin[9]==1) state+=128;
    if (state != last) { ret=1; last=state; }
    return ret;
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    g.setColor(Color.black);
    g.fillRect(ox-6,oy-28,numPins()*4+4,32);
    if ((state & 1)==1) g.setColor(Color.red); else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 2)==2) g.setColor(Color.red); else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 4)==4) g.setColor(Color.red); else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 8)==8) g.setColor(Color.red); else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 16)==16) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 32)==32) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 64)==64) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    if ((state & 128)==128) g.setColor(Color.red);
      else g.setColor(Globals.darkRed);
    g.fillRect(ox-2,oy-22,5,18); ox+=8;
    }
  }

