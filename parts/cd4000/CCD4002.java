package parts.cd4000;
import parts.*;
public class CCD4002 extends Ic {
  public CCD4002() {
    super();
    name=new String("CD4002");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==1 || pn==13) return 1; else return 0;
    }
  public int run() {
    pin[15]=f_or(pin[2],pin[3]);
    pin[15]=f_or(pin[15],pin[4]);
    pin[1]=f_nor(pin[15],pin[5]);
    pin[15]=f_or(pin[9],pin[10]);
    pin[15]=f_or(pin[15],pin[11]);
    pin[13]=f_nor(pin[15],pin[12]);
    return 0;
    }
  }

