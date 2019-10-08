package parts.S7400;
import parts.*;
public class C74ls95 extends Ic {
  public C74ls95() {
    super();
    name=new String("74LS95");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn>=10 && pn<=13) return 1; else return 0;
    }
  public int run() {
    if ((pin[6]!=0) && (pin[22]!=0) && (pin[8]==0))
      {
        if (pin[2]!=0)  pin[20]=1; else pin[20]=0;
        if (pin[3]!=0)  pin[20]+=2;
        if (pin[4]!=0)  pin[20]+=4;
        if (pin[5]!=0)  pin[20]+=8;
     }
    if ((pin[6]==0) && (pin[21]!=0) && (pin[9]==0))
      {
        pin[20]=(pin[20]+pin[20]) & 15;
        if (pin[1]!=0)  pin[20]++;
     }
    disperse(20,13,12,11,10,30,30,30,30);
    pin[21]=pin[9];
    pin[22]=pin[8];
    return 0;
    }
  }

