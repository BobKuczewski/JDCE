package parts.S7400;
import parts.*;
public class C74ls30 extends Ic {
  public C74ls30() {
    super();
    name=new String("74LS30");
    pin=new int[15];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8) return 1; else return 0;
    }
  public int run() {
    if ((pin[1]!=0) && (pin[2]!=0) && (pin[3]!=0) &&
       (pin[4]!=0) && (pin[5]!=0) && (pin[6]!=0) &&
       (pin[11]!=0) && (pin[12]!=0)) pin[8]=0;
         else pin[8]=1;
    return 0;
    }
  }

