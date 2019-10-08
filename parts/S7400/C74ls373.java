package parts.S7400;
import parts.*;
public class C74ls373 extends Ic {
  public C74ls373() {
    super();
    name=new String("74LS373");
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
    if (pin[11]!=0) 
      {
        if (pin[3]!=0)  pin[22]=1; else pin[22]=0;
        if (pin[4]!=0)  pin[25]=1; else pin[25]=0;
        if (pin[7]!=0)  pin[26]=1; else pin[26]=0;
        if (pin[8]!=0)  pin[29]=1; else pin[29]=0;
        if (pin[13]!=0)  pin[32]=1; else pin[32]=0;
        if (pin[14]!=0)  pin[35]=1; else pin[35]=0;
        if (pin[17]!=0)  pin[36]=1; else pin[36]=0;
        if (pin[18]!=0)  pin[39]=1; else pin[39]=0;
     }
    pin[2]=pin[22]; pin[5]=pin[25];
    pin[6]=pin[26]; pin[9]=pin[29];
    pin[12]=pin[32]; pin[15]=pin[35];
    pin[16]=pin[36]; pin[19]=pin[39];
    if (pin[1]!=0) 
      {
        pin[2]=2; pin[5]=2; pin[6]=2; pin[9]=2;
        pin[12]=2; pin[15]=2; pin[16]=2; pin[19]=2;
     }
    return 0;
    }
  }

