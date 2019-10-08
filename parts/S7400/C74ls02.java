package parts.S7400;
import parts.*;
public class C74ls02 extends Ic {
  public C74ls02() {
    super();
    name=new String("74LS02");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==1 || pn==4 || pn==10 || pn==13) return 1; else return 0;
    }
  public int run() {
    pin[1]=f_nor(pin[2],pin[3]);
    pin[4]=f_nor(pin[5],pin[6]);
    pin[10]=f_nor(pin[9],pin[8]);
    pin[13]=f_nor(pin[12],pin[11]);
    return 0;
    }
  }

