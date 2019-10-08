package parts.S7400;
import parts.*;
public class C74265 extends Ic {
  public C74265() {
    super();
    name=new String("74265");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==3 || pn==6 || pn==7 || pn==9 || pn==10 ||
        pn==13 || pn==14) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_inv(pin[1]); pin[2]=f_inv(pin[3]);
    pin[13]=f_inv(pin[15]); pin[14]=f_inv(pin[13]);
    pin[6]=f_and(pin[4],pin[5]); pin[7]=f_inv(pin[6]);
    pin[10]=f_and(pin[11],pin[12]); pin[9]=f_inv(pin[10]);
    return 0;
    }
  }

