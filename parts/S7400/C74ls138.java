package parts.S7400;
import parts.*;
public class C74ls138 extends Ic {
  public C74ls138() {
    super();
    name=new String("74LS138");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=9 && pn<=15) || pn==7) return 1; else return 0;
    }
  public int run() {
    int i;
    for (i=9;i<=15;i++) pin[i]=1; pin[7]=1;
    if (pin[1]!=0)  i=1; else i=0;
    if (pin[2]!=0)  i+=2;
    if (pin[3]!=0)  i+=4;
    pin[20]=i;
    if ((pin[6]!=0) && (pin[4]==0) && (pin[5]==0))
     switch(pin[20]) {
      case 0:pin[15]=0; break;
      case 1:pin[14]=0; break;
      case 2:pin[13]=0; break;
      case 3:pin[12]=0; break;
      case 4:pin[11]=0; break;
      case 5:pin[10]=0; break;
      case 6:pin[9]=0; break;
      case 7:pin[7]=0; break;
     }
    return 0;
    }
  }

