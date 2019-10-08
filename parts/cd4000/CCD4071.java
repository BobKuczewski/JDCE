package parts.cd4000;
import parts.*;
public class CCD4071 extends Ic {
  public CCD4071() {
    super();
    name=new String("CD4071");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==4 || pn==10 || pn==11) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_or(pin[1],pin[2]);
    pin[4]=f_or(pin[5],pin[6]);
    pin[10]=f_or(pin[8],pin[9]);
    pin[11]=f_or(pin[12],pin[13]);
    return 0;
    }
  }

