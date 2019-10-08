package parts.cd4000;
import parts.*;
public class CCD4020 extends Ic {
  public CCD4020() {
    super();
    name=new String("CD4020");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=1 && pn<=7) || pn==9 || (pn>=12 && pn<=15)) return 1;
      else return 0;
    }
  public int run() {
    if (pin[10]==0 && pin[30]!=0) {
      pin[22]++;
      if (pin[22]==256) { pin[22]=0; pin[23]++; }
      if (pin[23]==64) pin[23]=0;
      }
    if (pin[11]!=0) {
      pin[22]=0; pin[23]=0;
      }
    disperse(22,9,31,31,7,5,4,6,13);
    disperse(23,12,14,15,1,2,3,0,0);
    pin[30]=pin[10];
    return 0;
    }
  }

