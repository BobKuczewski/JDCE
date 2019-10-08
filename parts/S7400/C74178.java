package parts.S7400;
import parts.*;
public class C74178 extends Ic {
  public C74178() {
    super();
    name=new String("74178");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==4 || pn==6 || pn==8 || pn==10) return 1; else return 0;
    }
  public int run() {
    if ((pin[9]!=0) && (pin[11]==0) && (pin[5]==0) && (pin[21]!=0))
      {
        if (pin[2]!=0)  pin[20]=1; else pin[20]=0;
        if (pin[1]!=0)  pin[20]+=2;
        if (pin[13]!=0)  pin[20]+=4;
        if (pin[12]!=0)  pin[20]+=8;
     }
    if ((pin[11]!=0) && (pin[5]==0) && (pin[21]!=0))
      {
        pin[20]=(pin[20]+pin[20]) & 15;
        if (pin[3]!=0)  pin[20]++;
     }
    if ((pin[20] & 1)==1)  pin[4]=1; else pin[4]=0;
    if ((pin[20] & 2)==2)  pin[6]=1; else pin[6]=0;
    if ((pin[20] & 4)==4)  pin[8]=1; else pin[8]=0;
    if ((pin[20] & 8)==8)  pin[10]=1; else pin[10]=0;
    pin[21]=pin[5];
    return 0;
    }
  }

