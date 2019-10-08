package parts.cdp1800;
import parts.*;
public class CCDP1883 extends Ic {
  int latch;
  public CCDP1883() {
    super();
    name=new String("CDP1883");
    pin=new int[41];
    latch=0;
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if (pn>=11 && pn<=19) return 1; else return 0;
    }
  public int run() {
    if (pin[1]!=0 && pin[9]==0) {
      pin[19]=pin[2];
      pin[18]=pin[3];
      pin[17]=pin[4];
      pin[16]=pin[5];
      pin[15]=pin[6];
      if (pin[5]!=0) latch=1; else latch=0;
      if (pin[6]!=0) latch+=2;
      }
    if (pin[9]==0) {
      pin[11]=1; pin[12]=1; pin[13]=1; pin[14]=1;
      switch (latch) {
        case 0:pin[14]=0; break;
        case 1:pin[13]=0; break;
        case 2:pin[12]=0; break;
        case 3:pin[11]=0; break;
        }
      } else {
      pin[11]=1; pin[12]=1; pin[13]=1; pin[14]=1;
      }
    return 0;
    }
  }

