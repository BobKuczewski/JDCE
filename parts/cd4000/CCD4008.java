package parts.cd4000;
import parts.*;
public class CCD4008 extends Ic {
  public CCD4008() {
    super();
    name=new String("CD4008");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn>=10 || pn<=14) return 1; else return 0;
    }
  public int run() {
    pin[20]=f_binin(pin[7],pin[5],pin[3],pin[1],0,0,0,0);
    pin[21]=f_binin(pin[6],pin[4],pin[2],pin[15],0,0,0,0);
    pin[22]=pin[20]+pin[21];
    if (pin[9]==0) pin[22]++;
    disperse(22,10,11,12,13,14,0,0,0);
    return 0;
    }
  }

