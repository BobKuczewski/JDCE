package parts.S7400;
import parts.*;
public class C74ls273 extends Ic {
  public C74ls273() {
    super();
    name=new String("74LS273");
    pin=new int[41];
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==5 || pn==6 || pn==9 || pn==12 || pn==15 ||
        pn==16 || pn==19) return 1; else return 0;
    }
  public int run() {
    if ((pin[11]!=0) && (pin[21]==0))
      {
        if (pin[3]!=0)  pin[2]=1; else pin[2]=0;
        if (pin[4]!=0)  pin[5]=1; else pin[5]=0;
        if (pin[7]!=0)  pin[6]=1; else pin[6]=0;
        if (pin[8]!=0)  pin[9]=1; else pin[9]=0;
        if (pin[13]!=0)  pin[12]=1; else pin[12]=0;
        if (pin[14]!=0)  pin[15]=1; else pin[15]=0;
        if (pin[17]!=0)  pin[16]=1; else pin[16]=0;
        if (pin[18]!=0)  pin[19]=1; else pin[19]=0;
     }
    if (pin[1]==0)
      {
        pin[2]=0; pin[5]=0; pin[6]=0; pin[9]=0;
        pin[12]=0; pin[15]=0; pin[16]=0; pin[19]=0;
     }
    pin[21]=pin[11];
    return 0;
    }
  }

