package parts.cdp1800;
import parts.*;
public class CCDP1852 extends Ic {
  int latchIn,latchOut;
  public CCDP1852() {
    super();
    name=new String("CDP1852");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if (pn==4 || pn==6 || pn==8 || pn==10 || pn==23 ||
        pn==15 || pn==17 || pn==19 || pn==21) return 1; else return 0;
    }
  private void write() {
    if ((latchOut & 1)==1) pin[4]=1; else pin[4]=0;
    if ((latchOut & 2)==2) pin[6]=1; else pin[6]=0;
    if ((latchOut & 4)==4) pin[8]=1; else pin[8]=0;
    if ((latchOut & 8)==8) pin[10]=1; else pin[10]=0;
    if ((latchOut & 16)==16) pin[15]=1; else pin[15]=0;
    if ((latchOut & 32)==32) pin[17]=1; else pin[17]=0;
    if ((latchOut & 64)==64) pin[19]=1; else pin[19]=0;
    if ((latchOut & 128)==128) pin[21]=1; else pin[21]=0;
    }
  private void read() {
    if (pin[3]!=0) latchIn=1; else latchIn=0;
    if (pin[5]!=0) latchIn+=2;
    if (pin[7]!=0) latchIn+=4;
    if (pin[9]!=0) latchIn+=8;
    if (pin[16]!=0) latchIn+=16;
    if (pin[18]!=0) latchIn+=32;
    if (pin[20]!=0) latchIn+=64;
    if (pin[22]!=0) latchIn+=128;
    }
  public int run() {
    int enable;
    if (pin[2]==0) {
      if (pin[11]!=0) read();
      if (pin[11]==0 && pin[30]!=0) { latchOut=latchIn; pin[23]=0; }
      if (pin[1]!=0 && pin[13]!=0) enable=1; else enable=0;
      if (enable==1) {
        write();
        } else {
        pin[4]=2; pin[6]=2; pin[8]=2; pin[10]=2;
        pin[15]=2; pin[17]=2; pin[19]=2; pin[21]=2;
        if (pin[31]==1) pin[23]=1;
        }
      } else {
      if (pin[1]==0 && pin[11]!=0 && pin[13]!=0) enable=1; else enable=0;
      if (enable==1) read();
      write();
      if ((pin[1]!=0 || pin[13]==0) && pin[31]==1) pin[23]=1;
      if (pin[23]==1 && pin[30]!=0 && pin[11]==0) pin[23]=0;
      }
    if (pin[14]==0) {
      latchIn=0; latchOut=0;
      if (pin[2]==0) {
        pin[23]=1;
        if (enable==1) write();
        } else {
        pin[23]=0;
        write();
        }
      }
    pin[30]=pin[11];
    pin[31]=enable;
    return 0;
    }
  }

