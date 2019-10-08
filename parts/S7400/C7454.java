package parts.S7400;
import parts.*;
public class C7454 extends Ic {
  public C7454() {
    super();
    name=new String("7454");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8) return 1; else return 0;
    }
  public int run() {
    pin[8]=f_nor4(f_and(pin[1],pin[13]),f_and(pin[9],pin[10]),
                  f_and(pin[2],pin[3]),f_and(pin[4],pin[5]));
    return 0;
    }
  }

