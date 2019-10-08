package parts.cd4000;
import parts.*;
public class CCD4009 extends Ic {
  public CCD4009() {
    super();
    name=new String("CD4009");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==4 || pn==6 || pn==10 || pn==12 || pn==15) return 1;
      else return 0;
    }
  public int run() {
    pin[2]=f_inv(pin[3]);
    pin[4]=f_inv(pin[5]);
    pin[6]=f_inv(pin[7]);
    pin[10]=f_inv(pin[9]);
    pin[12]=f_inv(pin[11]);
    pin[15]=f_inv(pin[14]);
    return 0;
    }
  }

