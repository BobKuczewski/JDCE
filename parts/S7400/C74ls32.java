package parts.S7400;
import parts.*;
public class C74ls32 extends Ic {
  public C74ls32() {
    super();
    name=new String("74LS32");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==6 || pn==8 || pn==11) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_or(pin[1],pin[2]);
    pin[6]=f_or(pin[4],pin[5]);
    pin[8]=f_or(pin[9],pin[10]);
    pin[11]=f_or(pin[12],pin[13]);
    return 0;
    }
  }

