package parts.S7400;
import parts.*;
public class C74ls183 extends Ic {
  public C74ls183() {
    super();
    name=new String("74LS183");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==10 || pn==6 || pn==8) return 1; else return 0;
    }
  public int run() {
    int i;
    if (pin[1]!=0)  i=1; else i=0;
    if (pin[3]!=0)  i+=2;
    if (pin[4]!=0)  i+=4;
    switch(i) {
      case 0:pin[6]=0; pin[5]=0; break;
      case 1:pin[6]=1; pin[5]=0; break;
      case 2:pin[6]=1; pin[5]=0; break;
      case 3:pin[6]=0; pin[5]=1; break;
      case 4:pin[6]=1; pin[5]=0; break;
      case 5:pin[6]=0; pin[5]=1; break;
      case 6:pin[6]=0; pin[5]=1; break;
      case 7:pin[6]=1; pin[5]=1; break;
     }
    if (pin[13]!=0)  i=1; else i=0;
    if (pin[12]!=0)  i+=2;
    if (pin[11]!=0)  i+=4;
    switch(i) {
      case 0:pin[8]=0; pin[10]=0; break;
      case 1:pin[8]=1; pin[10]=0; break;
      case 2:pin[8]=1; pin[10]=0; break;
      case 3:pin[8]=0; pin[10]=1; break;
      case 4:pin[8]=1; pin[10]=0; break;
      case 5:pin[8]=0; pin[10]=1; break;
      case 6:pin[8]=0; pin[10]=1; break;
      case 7:pin[8]=1; pin[10]=1; break;
     }
    return 0;
    }
  }

