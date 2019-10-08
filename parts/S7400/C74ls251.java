package parts.S7400;
import parts.*;
public class C74ls251 extends Ic {
  public C74ls251() {
    super();
    name=new String("74LS251");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==6) return 1; else return 0;
    }
  public int run() {
    int i,p;
    int mode=1;
    p=0;
    if (pin[7]!=0)
     if (mode==0)
       {
         pin[5]=0; pin[6]=1;
      }
      else
       {
         pin[5]=2; pin[6]=2;
      }
    else
      {
        if (pin[11]!=0)  i=1; else i=0;
        if (pin[10]!=0)  i+=2;
        if (pin[9]!=0)  i+=4;
        switch(i) {
          case 0:p=4; break; case 1:p=3; break;
          case 2:p=2; break; case 3:p=1; break;
          case 4:p=15; break; case 5:p=14; break;
          case 6:p=13; break; case 7:p=12; break;
         }
        if (pin[p]!=0)
          {
            pin[5]=1; pin[6]=0;
         }
        else
          {
            pin[5]=0; pin[6]=1;
         }
     }
    return 0;
    }
  }

