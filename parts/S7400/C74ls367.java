package parts.S7400;
import parts.*;
public class C74ls367 extends Ic {
  public C74ls367() {
    super();
    name=new String("74LS367");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==5 || pn==7 || pn==9 || pn==11 || pn==13)
      return 1; else return 0;
    }
  public int run() {
    pin[3]=f_tri(pin[2],pin[1]);
    pin[5]=f_tri(pin[4],pin[1]);
    pin[7]=f_tri(pin[6],pin[1]);
    pin[9]=f_tri(pin[10],pin[1]);
    pin[11]=f_tri(pin[12],pin[15]);
    pin[13]=f_tri(pin[14],pin[15]);
    return 0;
    }
  }

