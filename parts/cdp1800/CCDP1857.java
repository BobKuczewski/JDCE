package parts.cdp1800;
import parts.*;
public class CCDP1857 extends Ic {
  public CCDP1857() {
    super();
    name=new String("CDP1857");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn>=11 && pn<=14) {
      if (pin[10]!=0) return 129; else return 128;
      } else return 0;
    }
  public int run() {
    if (pin[15]==0) {
      pin[11]=2; pin[12]=2; pin[13]=2; pin[14]=2;
      pin[3]=2; pin[4]=2; pin[5]=2; pin[6]=2;
      } else if (pin[10]!=0) {
      pin[14]=pin[1]; pin[13]=pin[2]; pin[12]=pin[7]; pin[11]=pin[9];
      pin[3]=2; pin[4]=2; pin[5]=2; pin[6]=2;
      } else {
      pin[3]=pin[14]; pin[4]=pin[13]; pin[5]=pin[12]; pin[6]=pin[11];
      }
    return 0;
    }
  }

