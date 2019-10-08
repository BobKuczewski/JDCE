package parts.S7400;
import parts.*;
public class C74150 extends Ic {
  public C74150() {
    super();
    name=new String("74150");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if (pn==10) return 1; else return 0;
    }
  public int run() {
    int i,p;
    p=0;
    if (pin[9]!=0)
      {
        pin[10]=1;
     }
    else
      {
        if (pin[15]!=0)  i=1; else i=0;
        if (pin[14]!=0)  i+=2;
        if (pin[13]!=0)  i+=4;
        if (pin[11]!=0)  i+=8;
        switch(i) {
          case 0:p=8; break; case 1:p=7; break;
          case 2:p=6; break; case 3:p=5; break;
          case 4:p=4; break; case 5:p=3; break;
          case 6:p=2; break; case 7:p=1; break;
          case 8:p=23; break; case 9:p=22; break;
          case 10:p=21; break; case 11:p=20; break;
          case 12:p=19; break; case 13:p=18; break;
          case 14:p=17; break; case 15:p=16; break;
         }
        if (pin[p]!=0)  pin[10]=0; else pin[10]=1;
     }
    return 0;
    }
  }

