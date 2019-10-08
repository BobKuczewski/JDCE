package parts.S7400;
import parts.*;
public class C74ls253 extends Ic {
  public C74ls253() {
    super();
    name=new String("74LS253");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==7 || pn==9) return 1; else return 0;
    }
  public int run() {
    int i,p;
    int mode=1;
    p=2;
    if (pin[1]!=0)
     {
      if (mode==0)  pin[7]=0; else pin[7]=2;
    }
    else
      {
        if (pin[14]!=0)  i=1; else i=0;
        if (pin[2]!=0)  i+=2;
        switch(i) {
          case 0:p=6; break; case 1:p=5; break;
          case 2:p=4; break; case 3:p=3; break;
         }
        if (pin[p]!=0)
          pin[7]=1;
        else
          pin[7]=0;
     }
    if (pin[15]!=0)
     {
      if (mode==0)  pin[9]=0; else pin[9]=2;
    }
    else
      {
        if (pin[14]!=0)  i=1; else i=0;
        if (pin[2]!=0)  i+=2;
        switch(i) {
          case 0:p=10; break; case 1:p=11; break;
          case 2:p=12; break; case 3:p=13; break;
         }
        if (pin[p]!=0)
          pin[9]=1;
        else
          pin[9]=0;
     }
    return 0;
    }
  }

