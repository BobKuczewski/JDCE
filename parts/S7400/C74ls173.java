package parts.S7400;
import parts.*;
public class C74ls173 extends Ic {
  public C74ls173() {
    super();
    name=new String("74LS173");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn>=3 && pn<=6) return 1; else return 0;
    }
  public int run() {
    int i;
    if (pin[15]!=0)
      {
        for (i=3;i<=6;i++) pin[20+i]=0;
     }
   else
      {
        if ((pin[9]==0) && (pin[10]==0) && (pin[7]!=0) && (pin[20]==0))
          {
            if (pin[14]!=0)  pin[23]=1; else pin[23]=0;
            if (pin[13]!=0)  pin[24]=1; else pin[24]=0;
            if (pin[12]!=0)  pin[25]=1; else pin[25]=0;
            if (pin[11]!=0)  pin[26]=1; else pin[26]=0;
         }
     }
    if ((pin[1]==0) && (pin[2]==0))
      {
        for (i=3;i<=6;i++) pin[i]=pin[20+i];
     }
   else
      {
        for (i=3;i<=6;i++) pin[i]=2;
     }
    pin[20]=pin[7];
    return 0;
    }
  }

