package parts.S7400;
import parts.*;
public class C74ls20 extends Ic {
  public C74ls20() {
    super();
    name=new String("74LS20");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==8) return 1; else return 0;
    }
  public int run() {
    pin[6]=f_nand4(pin[1],pin[2],pin[4],pin[5]);
    pin[8]=f_nand4(pin[9],pin[10],pin[12],pin[13]);
    return 0;
    }
  }

