package parts.S7400;
import parts.*;
public class C74ls10 extends Ic {
  public C74ls10() {
    super();
    name=new String("74LS10");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==12 || pn==6 || pn==8) return 1; else return 0;
    }
  public int run() {
    pin[12]=f_nand3(pin[1],pin[2],pin[13]);
    pin[6]=f_nand3(pin[3],pin[4],pin[5]);
    pin[8]=f_nand3(pin[9],pin[10],pin[11]);
    return 0;
    }
  }

