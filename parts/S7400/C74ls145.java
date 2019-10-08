package parts.S7400;
import parts.*;
public class C74ls145 extends Ic {
  public C74ls145() {
    super();
    name=new String("74LS145");
    pin=new int[17];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=1 && pn<=7) || (pn>=9 && pn<=11)) return 1; else return 0;
    }
  public int run() {
    int valu;
    pin[1]=1; pin[2]=1; pin[3]=1; pin[4]=1;
    pin[5]=1; pin[6]=1; pin[7]=1; pin[9]=1;
    pin[10]=1; pin[11]=1;
    if (pin[15]!=0)  valu=1; else valu=0;
    if (pin[14]!=0)  valu+=2;
    if (pin[13]!=0)  valu+=4;
    if (pin[12]!=0)  valu+=8;
    switch(valu) {
      case 0:pin[1]=0; break; case 1:pin[2]=0; break; 
      case 2:pin[3]=0; break; case 3:pin[4]=0; break; 
      case 4:pin[5]=0; break; case 5:pin[6]=0; break;
      case 6:pin[7]=0; break; case 7:pin[9]=0; break; 
      case 8:pin[10]=0; break; case 9:pin[11]=0; break;
      }
    return 0;
    }
  }

