package parts.S7400;
import parts.*;
public class C74ls08 extends Ic {
  public C74ls08() {
    super();
    name=new String("74LS08");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==6 || pn==8 || pn==11) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_and(pin[1],pin[2]);
    pin[6]=f_and(pin[4],pin[5]);
    pin[8]=f_and(pin[9],pin[10]);
    pin[11]=f_and(pin[12],pin[13]);
    return 0;
    }
  }

