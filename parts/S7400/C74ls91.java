package parts.S7400;
import parts.*;
public class C74ls91 extends Ic {
  public C74ls91() {
    super();
    name=new String("74LS91");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==13 || pn==14) return 1; else return 0;
    }
  public int run() {
    if ((pin[9]!=0) && (pin[21]==0)) 
      {
        pin[20]+=pin[20];
        if ((pin[11]!=0) && (pin[12]!=0)) 
          pin[20]++;
     }
    if ((pin[20] & 128)==128) 
      {
        pin[13]=1;
        pin[14]=0;
      }
    else
      {
        pin[13]=0;
        pin[14]=1;
     }
    pin[21]=pin[9];
    return 0;
    }
  }

