package parts.cd4000;
import parts.*;
public class CCD4000 extends Ic {
  public CCD4000() {
    super();
    name=new String("CD4000");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==9 || pn==10) return 1; else return 0;
    }
  public int run() {
    pin[15]=f_or(pin[3],pin[4]);
    pin[6]=f_nor(pin[15],pin[5]);
    pin[15]=f_or(pin[11],pin[12]);
    pin[10]=f_nor(pin[15],pin[13]);
    pin[9]=f_inv(pin[8]);
    return 0;
    }
  }

