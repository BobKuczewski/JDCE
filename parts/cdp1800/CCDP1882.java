package parts.cdp1800;
import parts.*;
public class CCDP1882 extends Ic {
  int latch;
  public CCDP1882() {
    super();
    name=new String("CDP1882");
    pin=new int[41];
    latch=0;
    }
  public int numPins() {
    return 18;
    }
  public int pinOut(int pn) {
    if (pn>=10 && pn<=17) return 1; else return 0;
    }
  public int run() {
    if (pin[1]!=0 && pin[8]==0) {
      pin[17]=pin[7];
      pin[16]=pin[6];
      pin[15]=pin[5];
      pin[14]=pin[4];
      if (pin[3]!=0) latch=1; else latch=0;
      if (pin[2]!=0) latch+=2;
      }
    if (pin[8]==0) {
      pin[10]=1; pin[11]=1; pin[12]=1; pin[13]=1;
      switch (latch) {
        case 0:pin[13]=0; break;
        case 1:pin[12]=0; break;
        case 2:pin[11]=0; break;
        case 3:pin[10]=0; break;
        }
      } else {
      pin[10]=1; pin[11]=1; pin[12]=1; pin[13]=1;
      }
    return 0;
    }
  }

