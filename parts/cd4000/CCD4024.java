package parts.cd4000;
import parts.*;
public class CCD4024 extends Ic {
  public CCD4024() {
    super();
    name=new String("CD4024");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if ((pn>=3 && pn<=6) || pn==9 || pn==11 || pn==12) return 1;
      else return 0;
    }
  public int run() {
    if (pin[1]==0 && pin[30]!=0) {
      pin[22]++;
      if (pin[22]==128) pin[22]=0;
      }
    if (pin[2]!=0) pin[22]=0;
    disperse(22,12,11,9,6,5,4,3,0);
    pin[30]=pin[1];
    return 0;
    }
  }

