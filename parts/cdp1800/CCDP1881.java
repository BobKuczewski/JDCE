package parts.cdp1800;
import parts.*;
public class CCDP1881 extends Ic {
  int latch;
  public CCDP1881() {
    super();
    name=new String("CDP1881");
    pin=new int[41];
    latch=0;
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if (pn>=12 && pn<=19) return 1; else return 0;
    }
  public int run() {
    int enable=0;
    if (pin[11]==0 && (pin[8]==0 || pin[9]==0)) enable=1; else enable=0;
    if (pin[1]!=0 && enable==1) {
      pin[19]=pin[7];
      pin[18]=pin[6];
      pin[17]=pin[5];
      pin[16]=pin[4];
      if (pin[3]!=0) latch=1; else latch=0;
      if (pin[2]!=0) latch+=2;
      }
    if (enable==1) {
      pin[12]=1; pin[13]=1; pin[14]=1; pin[15]=1;
      switch (latch) {
        case 0:pin[15]=0; break;
        case 1:pin[14]=0; break;
        case 2:pin[13]=0; break;
        case 3:pin[12]=0; break;
        }
      } else {
      pin[12]=1; pin[13]=1; pin[14]=1; pin[15]=1;
      }
    return 0;
    }
  }

