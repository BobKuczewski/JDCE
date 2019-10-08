package parts.S7400;
import parts.*;
public class C7425 extends Ic {
  public C7425() {
    super();
    name=new String("7425");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==8) return 1; else return 0;
    }
  public int run() {
    if (pin[3]!=0)
      pin[6]=f_nor4(pin[1],pin[2],pin[4],pin[5]);
    else
      pin[6]=1;
    if (pin[11]!=0)
      pin[8]=f_nor4(pin[9],pin[10],pin[12],pin[13]);
    else
      pin[8]=1;
    return 0;
      }
  }

