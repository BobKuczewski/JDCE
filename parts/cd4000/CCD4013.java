package parts.cd4000;
import parts.*;
public class CCD4013 extends Ic {
  public CCD4013() {
    super();
    name=new String("CD4013");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==1 || pn==2 || pn==12 || pn==13) return 1;
      else return 0;
    }
  public int run() {
    pin[24]=f_inv(pin[4]);
    pin[26]=f_inv(pin[6]);
    pin[28]=f_inv(pin[8]);
    pin[30]=f_inv(pin[10]);
    dff(26,24,3,5,1,2,20);
    dff(28,30,11,9,13,12,21);
    return 0;
    }
  }

