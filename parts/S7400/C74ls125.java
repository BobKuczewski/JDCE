package parts.S7400;
import parts.*;
public class C74ls125 extends Ic {
  public C74ls125() {
    super();
    name=new String("74LS125");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==6 || pn==8 || pn==11) return 1; else return 0;
    }
  public int run() {
    if (pin[1]!=0)
      pin[3]=2;
   else
      {
        if (pin[2]!=0)  pin[3]=1; else pin[3]=0;
     }
    if (pin[4]!=0)
      pin[6]=2;
   else
      {
        if (pin[5]!=0)  pin[6]=1; else pin[6]=0;
     }
    if (pin[10]!=0)
      pin[8]=2;
   else
      {
        if (pin[9]!=0)  pin[8]=1; else pin[8]=0;
     }
    if (pin[13]!=0)
      pin[11]=2;
   else
      {
        if (pin[12]!=0)  pin[11]=1; else pin[11]=0;
     }
    return 0;
    }
  }

