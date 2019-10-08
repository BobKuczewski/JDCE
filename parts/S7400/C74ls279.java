package parts.S7400;
import parts.*;
public class C74ls279 extends Ic {
  public C74ls279() {
    super();
    name=new String("74LS279");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==4 || pn==7 || pn==9 || pn==13) return 1; else return 0;
    }
  public int run() {
    if ((pin[5]!=0) && (pin[6]==0))  pin[7]=1;
    if ((pin[5]==0) && (pin[6]!=0))  pin[7]=0;
    if ((pin[5]==0) && (pin[6]==0))  pin[7]=1;
    if ((pin[14]!=0) && (pin[15]==0))  pin[13]=1;
    if ((pin[14]==0) && (pin[15]!=0))  pin[13]=0;
    if ((pin[14]==0) && (pin[15]==0))  pin[13]=1;
    if ((pin[1]!=0) && ((pin[2]==0) || (pin[3]==0)))  pin[4]=1;
    if ((pin[1]==0) && ((pin[2]!=0) && (pin[3]!=0)))  pin[4]=0;
    if ((pin[1]==0) && ((pin[2]==0) || (pin[3]==0)))  pin[4]=1;
    if ((pin[10]!=0) && ((pin[11]==0) || (pin[12]==0)))  pin[9]=1;
    if ((pin[10]==0) && ((pin[11]!=0) && (pin[12]!=0)))  pin[9]=0;
    if ((pin[10]==0) && ((pin[11]==0) || (pin[12]==0)))  pin[9]=1;
    return 0;
    }
  }

