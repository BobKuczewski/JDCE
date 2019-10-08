package parts.S7400;
import parts.*;
public class C74ls83 extends Ic {
  public C74ls83() {
    super();
    name=new String("74LS83");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==6 || pn==9 || pn==14 || pn==15)
      return 1; else return 0;
    }
  public int run() {
    int i;
    if (pin[10]!=0)  i=1; else i=0;
    if (pin[8]!=0)  i+=2;
    if (pin[3]!=0)  i+=4;
    if (pin[1]!=0)  i+=8;
    if (pin[11]!=0)  i++;
    if (pin[7]!=0)  i+=2;
    if (pin[4]!=0)  i+=4;
    if (pin[16]!=0)  i+=8;
    if (pin[13]!=0)  i++;
    pin[30]=i;
    disperse(30,9,6,2,15,14,40,40,40);
    return 0;
    }
  }

