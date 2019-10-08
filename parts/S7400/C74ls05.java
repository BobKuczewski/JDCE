package parts.S7400;
import parts.*;
public class C74ls05 extends Ic {
  public C74ls05() {
    super();
    name=new String("74LS05");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==4 || pn==6 || pn==8 || pn==10 || pn==12)
      return 1; else return 0;
    }
  public int run() {
    pin[2]=f_inv(pin[1]);
    pin[4]=f_inv(pin[3]);
    pin[6]=f_inv(pin[5]);
    pin[8]=f_inv(pin[9]);
    pin[10]=f_inv(pin[11]);
    pin[12]=f_inv(pin[13]);
    return 0;
    }
  }

