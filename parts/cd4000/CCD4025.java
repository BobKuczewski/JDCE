package parts.cd4000;
import parts.*;
public class CCD4025 extends Ic {
  public CCD4025() {
    super();
    name=new String("CD4025");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==9 || pn==10) return 1;
      else return 0;
    }
  public int run() {
    pin[15]=f_or(pin[3],pin[4]);
    pin[6]=f_nor(pin[15],pin[5]);
    pin[15]=f_or(pin[1],pin[2]);
    pin[9]=f_nor(pin[15],pin[8]);
    pin[15]=f_or(pin[11],pin[12]);
    pin[10]=f_nor(pin[15],pin[13]);
    return 0;
    }
  }

