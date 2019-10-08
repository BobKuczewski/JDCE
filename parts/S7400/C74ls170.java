package parts.S7400;
import parts.*;
public class C74ls170 extends Ic {
  public C74ls170() {
    super();
    name=new String("74LS170");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==7 || pn==9 || pn==10) return 1; else return 0;
    }
  public int run() {
    int i;
    byte j;
    if (pin[12]==0)
      {
        if (pin[14]!=0)  i=1; else i=0;
        if (pin[13]!=0)  i+=2;
        if (pin[15]!=0)  j=1; else j=0;
        if (pin[1]!=0)  j+=2;
        if (pin[2]!=0)  j+=4;
        if (pin[3]!=0)  j+=8;
        pin[20+i]=j;
     }
    if (pin[11]==0)
      {
        if (pin[5]!=0)  i=1; else i=0;
        if (pin[4]!=0)  i+=2;
        if ((pin[20+i] & 1)==1)  pin[10]=1; else pin[10]=0;
        if ((pin[20+i] & 2)==2)  pin[9]=1; else pin[9]=0;
        if ((pin[20+i] & 4)==4)  pin[7]=1; else pin[7]=0;
        if ((pin[20+i] & 8)==8)  pin[6]=1; else pin[6]=0;
     }
    if (pin[11]!=0)
      {
        pin[10]=0;
        pin[9]=0;
        pin[7]=0;
        pin[6]=0;
     }
    return 0;
    }
  }

