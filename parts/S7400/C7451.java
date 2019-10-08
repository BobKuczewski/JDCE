package parts.S7400;
import parts.*;
public class C7451 extends Ic {
  public C7451() {
    super();
    name=new String("7451");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==8) return 1; else return 0;
    }
  public int run() {
    pin[8]=f_nor(f_and(pin[1],pin[13]),f_and(pin[9],pin[10]));
    pin[6]=f_nor(f_and(pin[2],pin[3]),f_and(pin[4],pin[5]));
    return 0;
    }
  }

