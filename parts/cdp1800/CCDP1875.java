package parts.cdp1800;
import parts.*;
public class CCDP1875 extends Ic {
  int latch;
  public CCDP1875() {
    super();
    name=new String("CDP1875");
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
    if ((latch & 1)==1) pin[3]=1; else pin[3]=0;
    if ((latch & 2)==2) pin[5]=1; else pin[5]=0;
    if ((latch & 4)==4) pin[7]=1; else pin[7]=0;
    if ((latch & 8)==8) pin[9]=1; else pin[9]=0;
    if ((latch & 16)==16) pin[14]=1; else pin[14]=0;
    if ((latch & 32)==32) pin[16]=1; else pin[16]=0;
    if ((latch & 64)==64) pin[18]=1; else pin[18]=0;
    if ((latch & 128)==128) pin[20]=1; else pin[20]=0;
    }
  private void read() {
    if (pin[2]!=0) latch=1; else latch=0;
    if (pin[4]!=0) latch+=2;
    if (pin[6]!=0) latch+=4;
    if (pin[8]!=0) latch+=8;
    if (pin[15]!=0) latch+=16;
    if (pin[17]!=0) latch+=32;
    if (pin[19]!=0) latch+=64;
    if (pin[21]!=0) latch+=128;
    }
  public int run() {
    int enable=0;
    if (pin[1]==0 && pin[10]!=0 && pin[12]!=0) enable=1; else enable=0;
    if (enable==1 && pin[30]==0) read();
    if (pin[13]==0 && enable==0) latch=0;
    write();
    pin[30]=enable;
    return 0;
    }
  }

