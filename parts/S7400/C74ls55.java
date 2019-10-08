package parts.S7400;
import parts.*;
public class C74ls55 extends Ic {
  public C74ls55() {
    super();
    name=new String("74ls55");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8) return 1; else return 0;
    }
  public int run() {
    pin[8]=f_nor(f_and4(pin[1],pin[2],pin[3],pin[4]),
                 f_and4(pin[10],pin[11],pin[12],pin[13]));
    return 0;
    }
  }

