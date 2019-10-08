package parts.cd4000;
import parts.*;
public class CCD4023 extends Ic {
  public CCD4023() {
    super();
    name=new String("CD4023");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==10 || pn==9) return 1;
      else return 0;
    }
  public int run() {
    pin[15]=f_and(pin[3],pin[4]);
    pin[6]=f_nand(pin[15],pin[5]);
    pin[15]=f_and(pin[1],pin[2]);
    pin[9]=f_nand(pin[15],pin[8]);
    pin[15]=f_and(pin[11],pin[12]);
    pin[10]=f_nand(pin[15],pin[13]);
    return 0;
    }
  }

