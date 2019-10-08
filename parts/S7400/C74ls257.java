package parts.S7400;
import parts.*;
public class C74ls257 extends Ic {
  public C74ls257() {
    super();
    name=new String("74LS257");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==4 || pn==7 || pn==9 || pn==12) return 1; else return 0;
    }
  public int run() {
    int i;
    int mode=1;
    if (pin[15]!=0)
      {
        if (mode==0)  i=0; else i=2;
        pin[4]=i;
        pin[7]=i;
        pin[9]=i;
        pin[12]=i;
     }
    else
      {
        if (pin[1]==0)
          {
            if (pin[2]!=0)  pin[4]=1; else pin[4]=0;
            if (pin[5]!=0)  pin[7]=1; else pin[7]=0;
            if (pin[11]!=0)  pin[9]=1; else pin[9]=0;
            if (pin[14]!=0)  pin[12]=1; else pin[12]=0;
         }
        else
          {
            if (pin[3]!=0)  pin[4]=1; else pin[4]=0;
            if (pin[6]!=0)  pin[7]=1; else pin[7]=0;
            if (pin[10]!=0)  pin[9]=1; else pin[9]=0;
            if (pin[13]!=0)  pin[12]=1; else pin[12]=0;
         }
     }
    return 0;
    }
  }

