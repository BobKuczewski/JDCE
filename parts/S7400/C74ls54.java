package parts.S7400;
import parts.*;
public class C74ls54 extends Ic {
  public C74ls54() {
    super();
    name=new String("74ls54");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8) return 1; else return 0;
    }
  public int run() {
    pin[8]=f_nor4(f_and(pin[1],pin[2]),f_and3(pin[3],pin[4],pin[5]),
                  f_and(pin[12],pin[13]),f_and3(pin[9],pin[10],pin[11]));
    return 0;
    }
  }

