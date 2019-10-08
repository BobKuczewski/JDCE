package parts.S7400;
import parts.*;
public class C74ls244 extends Ic {
  public C74ls244() {
    super();
    name=new String("74LS244");
    pin=new int[41];
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if (pn==3 || pn==5 || pn==7 || pn==9 || pn==12 || pn==14 ||
        pn==16 || pn==18) return 1; else return 0;
    }
  public int run() {
    if (pin[1]!=0)
      {
        pin[12]=2; pin[14]=2; pin[16]=2; pin[18]=2;
     }
   else
      {
        if (pin[8]!=0)  pin[12]=1; else pin[12]=0;
        if (pin[6]!=0)  pin[14]=1; else pin[14]=0;
        if (pin[4]!=0)  pin[16]=1; else pin[16]=0;
        if (pin[2]!=0)  pin[18]=1; else pin[18]=0;
     }
    if (pin[19]!=0)
      {
        pin[3]=2; pin[5]=2; pin[7]=2; pin[9]=2;
     }
   else
      {
        if (pin[17]!=0)  pin[3]=1; else pin[3]=0;
        if (pin[15]!=0)  pin[5]=1; else pin[5]=0;
        if (pin[13]!=0)  pin[7]=1; else pin[7]=0;
        if (pin[11]!=0)  pin[9]=1; else pin[9]=0;
     }
    return 0;
    }
  }

