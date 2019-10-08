package parts.cdp1800;
import parts.*;
public class CCDP1874 extends Ic {
  int latchIn,latchOut;
  public CCDP1874() {
    super();
    name=new String("CDP1874");
    pin=new int[41];
    }
  public int numPins() {
    return 22;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==5 || pn==7 || pn==9 ||
        pn==14 || pn==16 || pn==18 || pn==20) return 1; else return 0;
    }
  private void write() {
    if ((latchOut & 1)==1) pin[3]=1; else pin[3]=0;
    if ((latchOut & 2)==2) pin[5]=1; else pin[5]=0;
    if ((latchOut & 4)==4) pin[7]=1; else pin[7]=0;
    if ((latchOut & 8)==8) pin[9]=1; else pin[9]=0;
    if ((latchOut & 16)==16) pin[14]=1; else pin[14]=0;
    if ((latchOut & 32)==32) pin[16]=1; else pin[16]=0;
    if ((latchOut & 64)==64) pin[18]=1; else pin[18]=0;
    if ((latchOut & 128)==128) pin[20]=1; else pin[20]=0;
    }
  private void read() {
    if (pin[2]!=0) latchIn=1; else latchIn=0;
    if (pin[4]!=0) latchIn+=2;
    if (pin[6]!=0) latchIn+=4;
    if (pin[8]!=0) latchIn+=8;
    if (pin[15]!=0) latchIn+=16;
    if (pin[17]!=0) latchIn+=32;
    if (pin[19]!=0) latchIn+=64;
    if (pin[21]!=0) latchIn+=128;
    }
  public int run() {
    if (pin[10]!=0 && pin[30]==0) read();
    if (pin[10]==0 && pin[30]!=0) latchOut=latchIn;
    if (pin[13]==0 && pin[10]==0) { latchIn=0; latchOut=0; }
    if (pin[1]!=0 && pin[12]!=0) write();
    pin[30]=pin[10];
    return 0;
    }
  }

