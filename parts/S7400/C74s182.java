package parts.S7400;
import parts.*;
public class C74s182 extends Ic {
  public C74s182() {
    super();
    name=new String("74S182");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=9 && pn<=12) || pn==7) return 1; else return 0;
    }
  public int run() {
    pin[7]=f_or4(pin[6],pin[15],pin[2],pin[4]);
    pin[10]=f_or4(f_and4(pin[5],pin[14],pin[1],pin[3]),
                      f_and4(pin[2],pin[5],pin[14],pin[1]),
                      f_and3(pin[15],pin[5],pin[14]),
                      f_and(pin[6],pin[5]));
    pin[9]=f_nor4(f_and4(pin[14],pin[1],pin[3],f_inv(pin[13])),
                      f_and4(pin[4],pin[14],pin[1],pin[3]),
                      f_and3(pin[2],pin[14],pin[1]),
                      f_and(pin[15],pin[14]));
    pin[11]=f_nor3(f_and3(pin[1],pin[3],f_inv(pin[13])),
                       f_and3(pin[4],pin[1],pin[3]),
                       f_and(pin[2],pin[1]));
    pin[12]=f_nor(f_and(pin[3],f_inv(pin[13])),
                      f_and(pin[4],pin[3]));
    return 0;
    }
  }

