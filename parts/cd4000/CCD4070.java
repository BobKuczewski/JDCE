package parts.cd4000;
import parts.*;
public class CCD4070 extends Ic {
  public CCD4070() {
    super();
    name=new String("CD4070");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==4 || pn==10 || pn==11) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_xor(pin[1],pin[2]);
    pin[4]=f_xor(pin[5],pin[6]);
    pin[10]=f_xor(pin[8],pin[9]);
    pin[11]=f_xor(pin[12],pin[13]);
    return 0;
    }
  }

