package parts.S7400;
import parts.*;
public class C74ls96 extends Ic {
  public C74ls96() {
    super();
    name=new String("74LS96");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==10 || pn==11 || (pn>=13 && pn<=15)) return 1; else return 0;
    }
  public int run() {
    if (pin[8]!=0)
      {
        if (pin[2]!=0)  pin[20]=(pin[20] | 1);
        if (pin[3]!=0)  pin[20]=(pin[20] | 2);
        if (pin[4]!=0)  pin[20]=(pin[20] | 4);
        if (pin[6]!=0)  pin[20]=(pin[20] | 8);
        if (pin[7]!=0)  pin[20]=(pin[20] | 16);
     }
    if ((pin[8]==0) && (pin[21]==0) && (pin[1]!=0))
      {
        pin[20]=(pin[20]+pin[20]) & 31;
        if (pin[9]!=0)  pin[20]++;
     }
    if (pin[16]==0)  pin[20]=0;
    disperse(20,15,14,13,11,10,30,30,30);
    pin[21]=pin[1];
    return 0;
    }
  }
