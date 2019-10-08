package parts.S7400;
import parts.*;
public class C74ls266 extends Ic {
  public C74ls266() {
    super();
    name=new String("74LS266");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==4 || pn==10 || pn==11) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_xnor(pin[1],pin[2]);
    pin[4]=f_xnor(pin[5],pin[6]);
    pin[10]=f_xnor(pin[8],pin[9]);
    pin[11]=f_xnor(pin[12],pin[13]);
    return 0;
    }
  }

