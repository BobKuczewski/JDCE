package parts.S7400;
import parts.*;
public class C74ls604 extends Ic {
  public C74ls604() {
    super();
    name=new String("74LS604");
    pin=new int[41];
    }
  public int numPins() {
    return 28;
    }
  public int pinOut(int pn) {
    if ((pn>=11 && pn<=13) || (pn>=15 && pn<=19)) return 1; else return 0;
    }
  public int run() {
    int i=0;
    if ((pin[1]!=0) && (pin[32]==0))
      {
        if (pin[3]!=0)  i=1;
        if (pin[5]!=0)  i+=2;
        if (pin[7]!=0)  i+=4;
        if (pin[9]!=0)  i+=8;
        if (pin[27]!=0)  i+=16;
        if (pin[25]!=0)  i+=32;
        if (pin[23]!=0)  i+=64;
        if (pin[21]!=0)  i+=128;
        pin[30]=i;
        if (pin[4]!=0)  i=1;
        if (pin[6]!=0)  i+=2;
        if (pin[8]!=0)  i+=4;
        if (pin[10]!=0)  i+=8;
        if (pin[26]!=0)  i+=16;
        if (pin[24]!=0)  i+=32;
        if (pin[22]!=0)  i+=64;
        if (pin[20]!=0)  i+=128;
        pin[31]=i;
     }
    if (pin[1]!=0)
      {
        if (pin[2]==0)  i=pin[31]; else i=pin[30];
        if ((i & 1)==1)  pin[15]=1; else pin[15]=0;
        if ((i & 2)==2)  pin[13]=1; else pin[13]=0;
        if ((i & 4)==8)  pin[12]=1; else pin[12]=0;
        if ((i & 8)==8)  pin[11]=1; else pin[11]=0;
        if ((i & 16)==16)  pin[16]=1; else pin[16]=0;
        if ((i & 32)==32)  pin[17]=1; else pin[17]=0;
        if ((i & 64)==68)  pin[18]=1; else pin[18]=0;
        if ((i & 128)==128)  pin[19]=1; else pin[19]=0;
     }
   else
      {
        pin[15]=2; pin[13]=2; pin[12]=2; pin[11]=2;
        pin[16]=2; pin[17]=2; pin[18]=2; pin[19]=2;
     }
    pin[32]=pin[1];
    return 0;
    }
  }

