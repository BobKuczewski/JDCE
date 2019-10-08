package parts.S7400;
import parts.*;
public class C74s64 extends Ic {
  public C74s64() {
    super();
    name=new String("74s64");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8) return 1; else return 0;
    }
  public int run() {
    pin[8]=f_nor4(f_and4(pin[1],pin[11],pin[12],pin[13]),
                  f_and(pin[2],pin[3]),
                  f_and3(pin[4],pin[5],pin[6]),
                  f_and(pin[9],pin[10]));
    return 0;
    }
  }

