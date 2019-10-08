package parts.S7400;
import parts.*;
public class C74ls165 extends Ic {
  public C74ls165() {
    super();
    name=new String("74LS165");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==7 || pn==9) return 1; else return 0;
    }
  public int run() {
    if (pin[1]==0)
      {
        if (pin[11]!=0)  pin[20]=1; else pin[20]=0;
        if (pin[12]!=0)  pin[20]+=2;
        if (pin[13]!=0)  pin[20]+=4;
        if (pin[14]!=0)  pin[20]+=8;
        if (pin[3]!=0)  pin[20]+=16;
        if (pin[4]!=0)  pin[20]+=32;
        if (pin[5]!=0)  pin[20]+=64;
        if (pin[6]!=0)  pin[20]+=128;
     }
    if ((pin[1]!=0) && (f_or(pin[15],pin[2])!=0) && (pin[21]==0))
      {
        pin[20]+=pin[20];
        if (pin[10]!=0)  pin[20]++;
     }
    if ((pin[20] & 128)==128)
      {
        pin[9]=1;
        pin[7]=0;
     }
    else
      {
        pin[9]=0;
        pin[7]=1;
     }
    pin[21]=f_or(pin[15],pin[2]);
    return 0;
    }
  }

