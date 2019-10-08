package parts.S7400;
import parts.*;
public class C74ls164 extends Ic {
  public C74ls164() {
    super();
    name=new String("74LS164");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if ((pn>=3 && pn<=6) || (pn>=10 && pn<=13)) return 1; else return 0;
    }
  public int run() {
    if (pin[9]==0)  pin[20]=0;
    if ((pin[9]!=0) && (pin[8]!=0) && (pin[21]==0))
      {
        pin[20]+=pin[20];
        if ((pin[1]!=0) && (pin[2]!=0))  pin[20]++;
     }
    if ((pin[20] & 1)==1)  pin[3]=1; else pin[3]=0;
    if ((pin[20] & 2)==2)  pin[4]=1; else pin[4]=0;
    if ((pin[20] & 4)==4)  pin[5]=1; else pin[5]=0;
    if ((pin[20] & 8)==8)  pin[6]=1; else pin[6]=0;
    if ((pin[20] & 16)==16)  pin[10]=1; else pin[10]=0;
    if ((pin[20] & 32)==32)  pin[11]=1; else pin[11]=0;
    if ((pin[20] & 64)==64)  pin[12]=1; else pin[12]=0;
    if ((pin[20] & 128)==128)  pin[13]=1; else pin[13]=0;
    pin[21]=pin[8];
    return 0;
    }
  }

