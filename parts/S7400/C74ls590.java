package parts.S7400;
import parts.*;
public class C74ls590 extends Ic {
  public C74ls590() {
    super();
    name=new String("74LS590");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=1 && pn<=7) || pn==9 || pn==15) return 1; else return 0;
    }
  public int run() {
    if ((pin[13]!=0) && (pin[21]==0))  pin[40]=pin[30];
    if ((pin[12]==0) && (pin[11]!=0) && (pin[20]==0))
      pin[30]++;
    if (pin[10]==0)  pin[30]=0;
    if (pin[14]==0)
      {
        if ((pin[40] & 1)==1)  pin[15]=1; else pin[15]=0;
        if ((pin[40] & 2)==2)  pin[1]=1; else pin[1]=0;
        if ((pin[40] & 4)==4)  pin[2]=1; else pin[2]=0;
        if ((pin[40] & 8)==8)  pin[3]=1; else pin[3]=0;
        if ((pin[40] & 16)==16)  pin[4]=1; else pin[4]=0;
        if ((pin[40] & 32)==32)  pin[5]=1; else pin[5]=0;
        if ((pin[40] & 64)==64)  pin[6]=1; else pin[6]=0;
        if ((pin[40] & 128)==128)  pin[7]=1; else pin[7]=0;
        if (pin[40]==255)  pin[9]=0; else pin[9]=1;
     }
   else
      {
        pin[1]=2; pin[2]=2; pin[3]=2; pin[4]=2;
        pin[5]=2; pin[6]=2; pin[7]=2; pin[15]=2;
        pin[9]=2;
     }
    pin[21]=pin[13];
    pin[20]=pin[11];
    return 0;
    }
  }

