package parts.S7400;
import parts.*;
public class C74s135 extends Ic {
  public C74s135() {
    super();
    name=new String("74S135");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==7 || pn==9 || pn==13) return 1; else return 0;
    }
  public int run() {
    pin[3]=f_xor(pin[4],f_xor(pin[1],pin[2]));
    pin[7]=f_xor(pin[4],f_xor(pin[5],pin[6]));
    pin[9]=f_xor(pin[12],f_xor(pin[10],pin[11]));
    pin[13]=f_xor(pin[12],f_xor(pin[14],pin[15]));
    return 0;
    }
  }

